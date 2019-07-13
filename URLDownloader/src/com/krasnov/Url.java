package com.krasnov;

public class Url {
    String url, protocol, name, domain, mapping;
    boolean haveParams;
    public Url(String url) {
        this.url = url;
        protocol = Parser.getProtocol(url);
        name = Parser.getName(url);
        domain = Parser.getDomain(url);
        mapping = Parser.getMapping(url);
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getProtocol() {
        return protocol;
    }

    public void setProtocol(String protocol) {
        this.protocol = protocol;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDomain() {
        return domain;
    }

    public void setDomain(String domain) {
        this.domain = domain;
    }

    public String getMapping() {
        return mapping;
    }

    public void setMapping(String mapping) {
        this.mapping = mapping;
    }

    public boolean isHaveParams() {
        return haveParams;
    }

    public void setHaveParams(boolean haveParams) {
        this.haveParams = haveParams;
    }
}
