/**
 * Created by Didi Yulianto (anonimeact) on 27/01/2017.
 * author email didiyuliantos@gmail.com
 */

#include <jni.h>
#include <string>
#include <sstream>
#include <algorithm>
#include <vector>
#include <jni.h>

using namespace std;

void replaceAll(std::string &data, const std::string& toSearch, const std::string& replaceStr) {
    size_t pos = data.find(toSearch);
    while (pos != std::string::npos) {
        data.replace(pos, toSearch.size(), replaceStr);
        pos = data.find(toSearch, pos + replaceStr.size());
    }
}

extern "C"
JNIEXPORT jstring JNICALL
Java_com_anonimeact_snappy_1ed_EnDec_encrypt(JNIEnv *env, jobject _, jstring input, jstring scrambler) {

    const char *inC = env->GetStringUTFChars(input, nullptr);
    string fix = string(inC);
    const char *scram = env->GetStringUTFChars(scrambler, nullptr);
    string key = string(scram);

    if (fix.length() > 11) {
        char x7 = fix.at(0);
        char x8 = fix.at(6);
        char x9 = fix.at(1);
        char x10 = fix.at(10);

        fix[0] = x8;
        fix[6] = x7;
        fix[1] = x10;
        fix[10] = x9;
    }

    if (fix.length() > 1) {
        char x1 = fix.at(0);
        char x2 = fix.at(fix.length() - 1);
        fix[0] = x2;
        fix[fix.length() - 1] = x1;

        if (fix.length() > 4) {
            char x5 = fix.at(1);
            char x6 = fix.at(fix.length() - 2);
            fix[1] = x6;
            fix[fix.length() - 2] = x5;

            if (fix.length() > 7) {
                char x3 = fix.at(3);
                char x4 = fix.at(fix.length() - 3);

                fix[3] = x4;
                fix[fix.length() - 3] = x3;
            }
        }
    }
    replaceAll(fix, "A", "$");
    replaceAll(fix, "B", "A");
    replaceAll(fix, "$", "B");

    replaceAll(fix, "2", "$");
    replaceAll(fix, "C", "2");
    replaceAll(fix, "$", "C");

    replaceAll(fix, "I", "$");
    replaceAll(fix, "8", "I");
    replaceAll(fix, "$", "8");

    replaceAll(fix, "Q", "$");
    replaceAll(fix, "r", "Q");
    replaceAll(fix, "$", "r");

    replaceAll(fix, "3", "$");
    replaceAll(fix, "s", "3");
    replaceAll(fix, "$", "s");

    replaceAll(fix, "Y", "$");
    replaceAll(fix, "z", "Y");
    replaceAll(fix, "$", "z");

    replaceAll(fix, "X", "$");
    replaceAll(fix, "0", "X");
    replaceAll(fix, "$", "0");

    replaceAll(fix, "=", "$");
    replaceAll(fix, "7", "=");
    replaceAll(fix, "$", "7");

    if (key.length() == 15) {
        replace(fix.begin(), fix.end(), key[0], '|');
        replace(fix.begin(), fix.end(), key[1], '>');
        replace(fix.begin(), fix.end(), key[2], ';');
        replace(fix.begin(), fix.end(), key[3], '*');
        replace(fix.begin(), fix.end(), key[4], '#');
        replace(fix.begin(), fix.end(), key[5], ')');
        replace(fix.begin(), fix.end(), key[6], '<');
        replace(fix.begin(), fix.end(), key[7], '&');
        replace(fix.begin(), fix.end(), key[8], '~');
        replace(fix.begin(), fix.end(), key[9], '$');
        replace(fix.begin(), fix.end(), key[10], '(');
        replace(fix.begin(), fix.end(), key[11], '?');
        replace(fix.begin(), fix.end(), key[12], '%');
        replace(fix.begin(), fix.end(), key[13], '.');
        replace(fix.begin(), fix.end(), key[14], ',');
    } else {
        replace(fix.begin(), fix.end(), 'g', '|');
        replace(fix.begin(), fix.end(), 'f', '>');
        replace(fix.begin(), fix.end(), 'e', ';');
        replace(fix.begin(), fix.end(), 'd', '*');
        replace(fix.begin(), fix.end(), 'c', '#');
        replace(fix.begin(), fix.end(), 'b', ')');
        replace(fix.begin(), fix.end(), 'a', '<');
        replace(fix.begin(), fix.end(), '8', '&');
        replace(fix.begin(), fix.end(), '7', '~');
        replace(fix.begin(), fix.end(), '6', '$');
        replace(fix.begin(), fix.end(), '5', '(');
        replace(fix.begin(), fix.end(), '4', '?');
        replace(fix.begin(), fix.end(), '3', '%');
        replace(fix.begin(), fix.end(), '2', '.');
        replace(fix.begin(), fix.end(), '1', ',');
    }
    replaceAll(fix, "X", "!");
    replaceAll(fix, "*", "X");
    replaceAll(fix, "!", "*");

    replaceAll(fix, ";", "!");
    replaceAll(fix, "l", ";");
    replaceAll(fix, "!", "l");

    replaceAll(fix, "j", "!");

    return env->NewStringUTF(fix.c_str());
}

extern "C"
JNIEXPORT jstring JNICALL
Java_com_anonimeact_snappy_1ed_EnDec_decrypt(
        JNIEnv *env, jobject _, jstring input, jstring scrambler) {

    const char *inC = env->GetStringUTFChars(input, nullptr);
    string output = string(inC);
    const char *scram = env->GetStringUTFChars(scrambler, nullptr);
    string key = string(scram);

    replaceAll(output, "!", "j");

    replaceAll(output, "l", "!");
    replaceAll(output, ";", "l");
    replaceAll(output, "!", ";");

    replaceAll(output, "*", "!");
    replaceAll(output, "X", "*");
    replaceAll(output, "!", "X");

    if (key.length() == 15) {
        replace(output.begin(), output.end(), '|', key[0]);
        replace(output.begin(), output.end(), '>', key[1]);
        replace(output.begin(), output.end(), ';', key[2]);
        replace(output.begin(), output.end(), '*', key[3]);
        replace(output.begin(), output.end(), '#', key[4]);
        replace(output.begin(), output.end(), ')', key[5]);
        replace(output.begin(), output.end(), '<', key[6]);
        replace(output.begin(), output.end(), '&', key[7]);
        replace(output.begin(), output.end(), '~', key[8]);
        replace(output.begin(), output.end(), '$', key[9]);
        replace(output.begin(), output.end(), '(', key[10]);
        replace(output.begin(), output.end(), '?', key[11]);
        replace(output.begin(), output.end(), '%', key[12]);
        replace(output.begin(), output.end(), '.', key[13]);
        replace(output.begin(), output.end(), ',', key[14]);
    }else {
        replace(output.begin(), output.end(), '|', 'g');
        replace(output.begin(), output.end(),'>', 'f');
        replace(output.begin(), output.end(), ';', 'e');
        replace(output.begin(), output.end(), '*', 'd');
        replace(output.begin(), output.end(), '#', 'c');
        replace(output.begin(), output.end(), ')', 'b');
        replace(output.begin(), output.end(), '<', 'a');
        replace(output.begin(), output.end(), '&', '8');
        replace(output.begin(), output.end(), '~', '7');
        replace(output.begin(), output.end(), '$', '6');
        replace(output.begin(), output.end(), '(', '5');
        replace(output.begin(), output.end(), '?', '4');
        replace(output.begin(), output.end(), '%', '3');
        replace(output.begin(), output.end(), '.', '2');
        replace(output.begin(), output.end(), ',', '1');
    }

    replaceAll(output, "7", "$");
    replaceAll(output, "=", "7");
    replaceAll(output, "$", "=");

    replaceAll(output, "0", "$");
    replaceAll(output, "X", "0");
    replaceAll(output, "$", "X");

    replaceAll(output, "z", "$");
    replaceAll(output, "Y", "z");
    replaceAll(output, "$", "Y");

    replaceAll(output, "s", "$");
    replaceAll(output, "3", "s");
    replaceAll(output, "$", "3");

    replaceAll(output, "r", "$");
    replaceAll(output, "Q", "r");
    replaceAll(output, "$", "Q");

    replaceAll(output, "8", "$");
    replaceAll(output, "I", "8");
    replaceAll(output, "$", "I");

    replaceAll(output, "C", "$");
    replaceAll(output, "2", "C");
    replaceAll(output, "$", "2");

    replaceAll(output, "B", "$");
    replaceAll(output, "A", "B");
    replaceAll(output, "$", "A");

    if (output.length() > 1) {
        char x1 = output.at(0);
        char x2 = output.at(output.length() - 1);
        output[0] = x2;
        output[output.length() - 1] = x1;

        if (output.length() > 4) {
            char x3 = output.at(1);
            char x4 = output.at(output.length() - 2);

            output[1] = x4;
            output[output.length() - 2] = x3;

            if (output.length() > 7) {
                char x5 = output.at(3);
                char x6 = output.at(output.length() - 3);

                output[3] = x6;
                output[output.length() - 3] = x5;
            }
        }
    }

    if (output.length() > 11) {
        char x7 = output.at(0);
        char x8 = output.at(6);
        char x9 = output.at(1);
        char x10 = output.at(10);

        output[0] = x8;
        output[6] = x7;
        output[1] = x10;
        output[10] = x9;
    }
    return env->NewStringUTF(output.c_str());
}

