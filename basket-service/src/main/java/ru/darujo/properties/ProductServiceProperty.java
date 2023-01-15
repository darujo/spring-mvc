package ru.darujo.properties;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.ConstructorBinding;

@ConstructorBinding
@ConfigurationProperties( prefix = "integration.product-service")
public class ProductServiceProperty implements PropertyConnectionInterface {
  private String url;
  private Integer connectionTimeOut;
  private Integer readTimeOut;
  private Integer writeTimeOut;

  public void setUrl(String url) {
    this.url = url;
  }

  public void setConnectionTimeOut(Integer connectionTimeOut) {
    this.connectionTimeOut = connectionTimeOut;
  }

  public void setReadTimeOut(Integer readTimeOut) {
    this.readTimeOut = readTimeOut;
  }

  public void setWriteTimeOut(Integer writeTimeOut) {
    this.writeTimeOut = writeTimeOut;
  }

  @Override
  public String getUrl() {
    return url;
  }

  @Override
  public Integer getConnectionTimeOut() {
    return connectionTimeOut;
  }

  @Override
  public Integer getReadTimeOut() {
    return readTimeOut;
  }

  @Override
  public Integer getWriteTimeOut() {
    return writeTimeOut;
  }
}
