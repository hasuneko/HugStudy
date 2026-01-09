package study;
/**
*
* 本課題では、インスタンス化、フィールド変数の基本的な使い方を学んでいきましょう。
* 課題範囲は以下のとおりです。
*   Task2_15: 問①から問④
* 指定された値と変数名を守って記述してください。
*
*/

public class Task2_15 {
    // ① redとblueにstatic修飾子とアクセス修飾子を追加
	//mainメソッド(static)内で直接使うため、変数にもstaticが必要。
	public static  String red = "赤";
	public static  String blue = "青";

    public static void main(String[] args) {
        // ② Bikeクラスをインスタンス化
        // 引数なしのコンストラクタを使用してbike1とbike2をインスタンス化する
    	Bike bike1 = new Bike();
    	Bike bike2 = new Bike();
       
       // ③ bike1とbike2にcheckを設定する(出力結果通りに)
       // check()メソッドはstateがtrueなら「走行中」、falseなら「停止中」を表示
    	bike1.run(); //stateをtrue(走行中)にする
    	bike2.stop(); //stateをfalse(停止中)にする
      
        
        // ④ bike1とbike2のtypeを変更する(出力結果通りに)
    	bike1.type = "250cc";
    	bike2.type = "250cc";
       

        System.out.println("car1は" + red + "色で" + bike1.type + "タイプです。 現在" + bike1.check());
        System.out.println("car2は" + blue + "色で" + bike2.type + "タイプです。 現在" + bike2.check());
    }
}

class Bike {
    String color;
    String type;
    Boolean state;

    // 引数なしのコンストラクタ
    public Bike() {
        this.color = "未設定";
        this.type = "250cc"; // デフォルト値として250ccを設定
    }

    public void run() {
        this.state = true;
    }

    public void stop() {
        this.state = false;
    }

    public String check() {
        if (this.state) {
            return "走行中です。";
        } else {
            return "停止中です。";
        }
    }
}