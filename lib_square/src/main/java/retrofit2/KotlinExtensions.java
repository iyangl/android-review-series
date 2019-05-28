package retrofit2;

/**
 * author: ly
 * date  : 2019/5/28 16:34
 * desc  : 随便创建的，为了不报错
 */
class KotlinExtensions {
    public static <ResponseT> Object awaitNullable(Call<ResponseT> call, Continuation<ResponseT> continuation) {
        return null;
    }

    public static <ResponseT> Object await(Call<ResponseT> call, Continuation<ResponseT> continuation) {
        return null;
    }

    public static <ResponseT> Object awaitResponse(Call<ResponseT> call, Continuation<Response<ResponseT>> continuation) {
        return null;
    }
}
