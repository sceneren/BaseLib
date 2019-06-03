package wiki.scene.baselibrary.net.exception

/**
 *
 * @Description:    接口状态
 * @Author:         scene
 * @CreateDate:     2019-06-03 18:03
 * @UpdateUser:     更新者：
 * @UpdateDate:     2019-06-03 18:03
 * @UpdateRemark:   更新说明：
 * @Version:        版本号：
 */
object ErrorStatus {
    /**
     * 响应成功
     */
    @JvmField
    val SUCCESS = 0

    /**
     * 未知错误
     */
    @JvmField
    val UNKNOWN_ERROR = 1002

    /**
     * 服务器内部错误
     */
    @JvmField
    val SERVER_ERROR = 1003

    /**
     * 网络连接超时
     */
    @JvmField
    val NETWORK_ERROR = 1004

    /**
     * API解析异常（或者第三方数据结构更改）等其他异常
     */
    @JvmField
    val API_ERROR = 1005

}