# IFramework
An advanced Item Framework for paper (NOT SPIGOT)
This IS NOT compatible with spigot (I don't plan on making it for a while, however make an issue if you really need)

Currently, Items are not dynamic, and you might have to write your own Builder implementation if you need, you can also make it as a contribution, if there is an open issue for it.

# EXAMPLES
[Hypixel-Like Item implementation](https://github.com/Wyvii/IFramework/tree/master/examples)

# Maven & Gradle
[![](https://jitpack.io/v/Wyvii/IFramework.svg)](https://jitpack.io/#Wyvii/IFramework)

# Maven

```xml
<repository>
  <id>jitpack.io</id>
  <url>https://jitpack.io</url>
</repository>
```

```xml
<dependency>
  <groupId>com.github.Wyvii</groupId>
  <artifactId>IFramework</artifactId>
  <version>Tag</version>
</dependency>
```

# Gradle

```groovy
allprojects {
    repositories {
    maven { url 'https://jitpack.io' }
  }
}
```

```groovy
dependencies {
  implementation 'com.github.Wyvii:IFramework:Tag'
}
```

