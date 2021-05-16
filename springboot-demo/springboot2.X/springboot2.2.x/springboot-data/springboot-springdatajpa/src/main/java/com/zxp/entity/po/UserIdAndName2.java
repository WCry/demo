package com.zxp.entity.po;

/**
 * 定义领域对象接口，实现领域部分需要对象
 */
public class UserIdAndName2 {
     private String id;
     private String name;
     private Integer dddDsad;

     public String getId() {
          return id;
     }

     public void setId(String id) {
          this.id = id;
     }

     public String getName() {
          return name;
     }

     public void setName(String name) {
          this.name = name;
     }

     public Integer getDddDsad() {
          return dddDsad;
     }

     public void setDddDsad(Integer dddDsad) {
          this.dddDsad = dddDsad;
     }

     public UserIdAndName2(String id, String name, Integer ddd){
          this.id=id;
          this.name=name;
          this.dddDsad=ddd;
     }

}