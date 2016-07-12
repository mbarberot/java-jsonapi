# java-jsonapi

[![Build Status](https://travis-ci.org/mbarberot/java-jsonapi.svg?branch=master)](https://travis-ci.org/mbarberot/java-jsonapi)
[![Coverage Status](https://coveralls.io/repos/github/mbarberot/java-jsonapi/badge.svg?branch=master)](https://coveralls.io/github/mbarberot/java-jsonapi?branch=master)
[![Jitpack Status](https://jitpack.io/v/mbarberot/java-jsonapi.svg)](https://jitpack.io/#mbarberot/java-jsonapi)

A simple JSON API <-> POJO converter

> WORK IN PROGRESS

## Why another lib ?

- Not tied to a json lib (such as Jackson of Gson)
- Not tied to a framework (such as Retrofit, Spring or JAX-RS)
- Multiple ways for entities "discovery"

When I searched for a JSON API, I was using a super light java framework and most libraries I found were tied to JAX-RS or Retrofit...  
I also had a separate module that contained my entities and I would not pollute this module's POM with a dependency JUST because of annotations.

## JSON conversion

This lib does not include any json conversion lib : you'll have to do it yourself.  
Here is an example for Jackson.

First, convert your entity :  
```java
Document jsonapidoc = converter.convertEntity(entity);
```

Second, configure your mapper :  
```java
ObjectMapper mapper =  new ObjectMapper();
mapper.setSerializationInclusion(JsonInclude.Include.NON_NULL);
```

Finally, enjoy :  
```java
mapper.writeValueAsString(jsonapidoc);
```
