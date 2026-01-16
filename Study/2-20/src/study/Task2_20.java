package study;

public class Task2_20 {

	public static void main(String[] args) {
		// ④ 子クラスを元にしたインスタンスを作成して下さい。(インスタンス名：child)
        Child child = new Child();
        
        // ⑤ インスタンスchildでcallNameメソッドを呼び出して下さい。
        child.callName();
        
        // ⑥ インスタンスchildでupdateメソッドを呼び出して下さい。
        child.update();
        
        // ⑦ オーバーロードされたargumentメソッドを呼び出し解答画像になるように出力して下さい。
        child.argument();          // 引数0つ
        child.argument(1);         // 引数1つ
        child.argument(1, 1);      // 引数2つ (内部で足し算されて「2つのもの」と表示される)
	}

}
