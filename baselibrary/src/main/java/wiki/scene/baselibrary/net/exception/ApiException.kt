package wiki.scene.baselibrary.net.exception

/**
 *
 * @Description:    自定义接口异常
 * @Author:         scene
 * @CreateDate:     2019-06-03 18:03
 * @UpdateUser:     更新者：
 * @UpdateDate:     2019-06-03 18:03
 * @UpdateRemark:   更新说明：
 * @Version:        版本号：
 */
class ApiException : RuntimeException {

    private var code: Int? = null


    constructor(throwable: Throwable, code: Int) : super(throwable) {
        this.code = code
    }

    constructor(message: String) : super(Throwable(message))
}