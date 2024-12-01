cmake_minimum_required(VERSION 3.4.1)

add_library( # Sets the name of the library.
            ed-lib

            # Sets the library as a shared library.
            SHARED

            # Provides a relative path to your source file(s).
            src/main/cpp/ed_lib.cpp )
add_library( # Sets the name of the library.
            snappy-android

            # Sets the library as a shared library.
            SHARED

            # Provides a relative path to your source file(s).
        src/main/cpp/snappy/snappy-native.h
        src/main/cpp/snappy/snappy-native.cc
        src/main/cpp/snappy/config.h
        src/main/cpp/snappy/snappy.h
        src/main/cpp/snappy/snappy.cc
        src/main/cpp/snappy/snappy-c.h
        src/main/cpp/snappy/snappy-c.cc
        src/main/cpp/snappy/snappy-internal.h
        src/main/cpp/snappy/snappy-sinksource.h
        src/main/cpp/snappy/snappy-sinksource.cc
        src/main/cpp/snappy/snappy-stubs-internal.cc
        src/main/cpp/snappy/snappy-stubs-internal.h
        src/main/cpp/snappy/snappy-stubs-public.h
)

find_library( # Sets the name of the path variable.
            log-lib

            # Specifies the name of the NDK library that
            # you want CMake to locate.
            log )

            target_link_libraries( # Specifies the target library.
                    ed-lib
                    snappy-android

            # Links the target library to the log library
            # included in the NDK.
            ${log-lib} )