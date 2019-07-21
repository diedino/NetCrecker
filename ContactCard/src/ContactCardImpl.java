import javafx.util.Pair;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.Period;
import java.time.ZoneId;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

enum Sex {
    Male(false), Female(true);
    private boolean isWoman;

    Sex(boolean b) {
        isWoman = b;
    }

    public boolean isWoman() {
        return isWoman;
    }
}

class EqualPair<K, V> extends Pair<K, V> {

    public EqualPair(K key, V value) {
        super(key, value);
    }

    @Override
    public boolean equals(Object o) {

        if (!(o instanceof EqualPair)) {
            return false;
        }

        EqualPair obj = (EqualPair) o;
        return this.getKey().equals(obj.getKey());
    }
}

public class ContactCardImpl implements ContactCard {

    private String FN;
    private String ORG;
    private Sex GENDER = Sex.Male;
    private Date BDAY;
    private Map<String, String> TEL = new HashMap<>();

    public ContactCardImpl() {

    }

    @Override
    public ContactCard getInstance(Scanner scanner) {
        LinkedList<EqualPair<String, String>> pairs = new LinkedList<>();
        while (scanner.hasNextLine()) {
            String[] line = scanner.nextLine().split("[:;]", 2);
            if (line.length < 2)
                throw new InputMismatchException();
            pairs.add(new EqualPair<>(line[0], line[1]));
        }

        boolean correct = isCorrect(pairs);
        if (!correct)
            throw new NoSuchElementException();

        for (EqualPair<String, String> pair : pairs) {
            if (pair.getKey().equals("FN"))
                FN = pair.getValue();
            if (pair.getKey().equals("ORG"))
                ORG = pair.getValue();
            if (pair.getKey().equals("GENDER")) {
                if (pair.getValue().equals("F") || pair.getValue().equals("M"))
                    GENDER = pair.getValue().equals("F") ? Sex.Female : Sex.Male;
                else
                    throw new InputMismatchException();

            }

            if (pair.getKey().equals("TEL")) {
                if (!ParseTel(pair.getValue()))
                    throw new InputMismatchException();
            }
            if (pair.getKey().equals("BDAY")) {
                SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
                try {
                    BDAY = sdf.parse(pair.getValue());
                } catch (ParseException e) {
                    throw new InputMismatchException();
                }
            }

        }
        return this;

    }

    private boolean ParseTel(String value) {
        String regex = "TYPE=(.*):(\\d{10})$";
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(value);
        if (matcher.groupCount() == 0)
            return false;
        while (matcher.find()) {
            String[] types = matcher.group(1).split(",");
            for (String type : types) {
                TEL.put(type, matcher.group(2));
            }
            return true;
        }
        return false;
    }

    private boolean isCorrect(LinkedList<EqualPair<String, String>> pairs) {
        boolean correct = isFirstBegin(pairs);
        correct = isLastEnd(pairs) && correct;
        correct = containRequired(pairs) && correct;
        return correct;
    }

    private boolean containRequired(LinkedList<EqualPair<String, String>> pairs) {
        boolean containsFN = false, containsORG = false;
        containsFN = pairs.contains(new EqualPair<>("FN", ""));
        containsORG = pairs.contains(new EqualPair<>("ORG", ""));
        return containsFN && containsORG;
    }

    private boolean isFirstBegin(LinkedList<EqualPair<String, String>> pairs) {
        boolean key = pairs.getFirst().getKey().equals("BEGIN");
        boolean val = pairs.getFirst().getValue().equals("VCARD");
        return key && val;
    }

    private boolean isLastEnd(LinkedList<EqualPair<String, String>> pairs) {
        boolean key = pairs.getLast().getKey().equals("END");
        boolean val = pairs.getLast().getValue().equals("VCARD");
        return key && val;
    }

    @Override
    public ContactCard getInstance(String data) {
        return getInstance(new Scanner(data));
    }

    @Override
    public String getFullName() {
        return FN;
    }

    @Override
    public String getOrganization() {
        return ORG;
    }

    @Override
    public boolean isWoman() {
        return GENDER.isWoman();
    }

    @Override
    public Calendar getBirthday() {
        if (BDAY == null)
            throw new NoSuchElementException();
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(BDAY);
        return calendar;
    }

    @Override
    public Period getAge() {
        LocalDate bday = BDAY.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
        LocalDate today = Calendar.getInstance().getTime().toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
        return Period.between(bday, today);
    }

    @Override
    public int getAgeYears() {
        return getAge().getYears();
    }

    @Override
    public String getPhone(String type) {
        String number = TEL.get(type);
        if (number == null)
            throw new NoSuchElementException();
        return number.replaceFirst("(\\d{3})(\\d{3})(\\d+)", "($1) $2-$3");
    }
}
