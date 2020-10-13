package id.ac.ui.cs.mobileprogramming.hira.helloworld.data

data class Grocery(val jumlah: Int, val name: String) {
    override fun toString(): String {
        return "${jumlah}x - $name"
    }
}