package com.krasnov;

public interface UrlInterface {
    /**
     *
     * @return full web address
     */
    public String getUrl();

    /**
     *
     * @return type of protocol
     */
    public String getProtocol();

    /**
     *
     * @return
     */
    public String getName();

    /**
     *
     * @return domain
     */
    public String getDomain();

    /**
     * url can consists with multimapping
     * but for task we need only last mapping
     * @return last mapping in url
     */
    public String getMapping();
}
