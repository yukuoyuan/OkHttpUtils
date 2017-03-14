package cn.yuan.yu.library;

/**
 * Created by yukoyuan on 16/7/10.
 * 这是一个下载文件的回调监听
 */
public interface DonwloadResponseListener {
    /**
     * @param bytesRead     已下载字节数
     * @param contentLength 总字节数
     * @param done          是否下载完成
     * @deprecated 计算方式是 (100 * bytesRead) / contentLength
     * 日志为 45%...
     */
    void OnSuccess(long bytesRead, long contentLength, boolean done);

    /**
     * 下载失败的回调方法
     */
    void onfailure();
}
