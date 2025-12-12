package sa.gov.absher.util

object AbsherUrls {
    const val REGISTER = "https://www.absher.sa/wps/portal/individuals/static/register/!ut/p/z1/04_iUlDg4tKPAFJABjKBwtGPykssy0xPLMnMz0vM0Y_Qj4wyizd1DnD2tPA1NnQPCDU3MHIzN_FyNvN2D7Mw0Q_Hq8DAXD-KGP0GOICjAXH68SiIwm-8F2ELovAqAXmRkCXBqXn6BbmhoRGVwQHpjoqKAJ_Z34g!/dz/d5/L0lHSkovd0RNQU5rQUVnQSEhLzROVkUvYXI!/"
    const val FORGOT_PASSWORD = "https://www.absher.sa/wps/portal/individuals/static/forgotPassword/"
}

expect fun openUrl(url: String)
