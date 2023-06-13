# snappy-ed

**snappy-ed** is a simple tool to compress string data along with randomization of the characters of the string.

This tool is perfect if you are creating an application that contains static data, especially using JSON stored in the raw or asset folders. From our test results, snappy can compress JSON files from 12 Mb to around 2 Mb. This tool uses the [Snappy Android](https://github.com/jiechic/Snappy-Android) library and randomizes compressed characters using the JNI class.

## Implementation
**Step 1.** Add it in your root build.gradle at the end of repositories:

```css
	allprojects {
		repositories {
			...
			maven { url 'https://jitpack.io' }
		}
	}
```

**Step 2.**  Add the dependency

```css
	dependencies {
	        implementation 'com.github.anonimeact:snappy-ed:Tag'
	}
```

## How to use

```css
	. . . . . . .
	Loader.encrypt("Your string data", "Your key")
	Loader.decrypt("Your string data", "Your key")

	/** encrypt a file and save in local storage in
		snappy-ed folder as a file*/
	Loader.encryptFile(context, "originFile.txt", "newEncryptedFile.txt", "Your key")
	. . . . . . .
```


## Key criteria:
 - You can user random letters (a...z / A...Z) and numbers (0...9)
 - Symbols of any kind are not allowed
 - The length of the key is 15, can't be less or more

*) **Note**

> **If the key is not filled, it will use the key stored in the logic library**


	
