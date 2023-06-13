# snappy-ed

**snappy-ed** is a simple tool to compress string data along with randomization of the characters of the string.

This tool is perfect if you are creating an application that contains static data, especially using JSON stored in the raw or asset folders. From our test results, snappy can compress JSON files from 12 Mb to around 2 Mb. This tool uses the [Snappy Android](https://github.com/jiechic/Snappy-Android) library and randomizes compressed characters using the JNI class.
