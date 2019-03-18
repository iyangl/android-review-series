LOCAL_PATH := $(call my-dir)
    include $(CLEAR_VARS)
    #LOG配置
    LOCAL_LDLIBS += -llog
    #编译生成的文件的类库叫什么名字
    LOCAL_MODULE    := hello
    #要编译的c文件
    LOCAL_SRC_FILES := hello.c
    include $(BUILD_SHARED_LIBRARY)