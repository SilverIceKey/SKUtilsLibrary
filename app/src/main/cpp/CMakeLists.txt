cmake_minimum_required(VERSION 3.4.1)

add_library( jniTools
             SHARED
             JNITools.cpp)

find_library( # Sets the name of the path variable.
              log-lib

              # Specifies the name of the NDK library that
              # you want CMake to locate.
              log )

#将预构建库关联到您自己的原生库
target_link_libraries( # Specifies the target library.
                        jniTools

                       # Links the target library to the log library
                       # included in the NDK.
                       ${log-lib} )