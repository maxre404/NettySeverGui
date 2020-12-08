package bean

fun main(args: Array<String>) {
    test()
}
fun test( vararg headStr: String){
    println(headStr.isNotEmpty())
//        println(headStr[0])
}