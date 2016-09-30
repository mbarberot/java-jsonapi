# java-jsonapi

[![Build Status](https://travis-ci.org/mbarberot/java-jsonapi.svg?branch=master)](https://travis-ci.org/mbarberot/java-jsonapi)
[![Coverage Status](https://coveralls.io/repos/github/mbarberot/java-jsonapi/badge.svg?branch=master)](https://coveralls.io/github/mbarberot/java-jsonapi?branch=master)
[![Jitpack Status](https://jitpack.io/v/mbarberot/java-jsonapi.svg)](https://jitpack.io/#mbarberot/java-jsonapi)

A simple JSON API <-> POJO converter

## What it does

- Convert your entities to a java structure that follow the json api specification
- Convert that java/json-api structure into your entities

## What it does not

- Serialize/Deszerialize from json or xml (just do it with your preferred lib)

## Why reinvent the wheel ?

Other libs I found where tied to a framework (such as Retrofit, Spring or JAX-RS) and/or tied to a json lib (such as Jackson of Gson).  
I would liked to have a simple lib that just convert my entities into a middle format which would be naturally serialized/deserialized into json or xml.

## Usage

Look at : 

- the Jitpack label on top of this README to add the lib to your favorite build system (mvn, gradle, ...)
- the JsonapiConverterTest test class to see how to create and use a converter
- the JacksonTest test class to see how to configure your mapper to work with Jackson
- the BookHelper test utility to see an example of converter configuration in plain java

## Roadmap

### v1

- [x] Publish beta version
- [ ] Improve the beta version and release a production version
- [ ] Publish a Javadoc

### v2

- [ ] Add possibility to use annotations to generate converter configuration
- [ ] Add possibility to use a yaml file to generate converter configuration
- [ ] Create a Github page for the project
- [ ] Communicate on the project (mainly try to add this lib to JSON API format website)
