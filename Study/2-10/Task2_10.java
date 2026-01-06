/**
* Task2-10 : 課題内容
*
* 本課題では、ルーブ文に記述に慣れていきましょう。
* 問①〜問④まであります。
* for文・while文の仕組みを意識しながらコーディングしていきましょう！
*/
public class Task2_10 {

    public static void main(String[] args) {

        // ① 「みかん」、「りんご」、「ぶどう」、「メロン」の値を設定した配列 fruits を作成してください。
        String[]fruits = {"みかん","りんご","ぶどう","メロン"};

        // ② for文を使って①で作成した配列を出力しなさい。
        for(int i =0; i< fruits.length; i++){
            System.out.println(fruits[i]);
        }

        // ③ 以下のwhile文の処理について、何をしているのかコメントを記入してください。
        /*
        * [変数　i　を1で初期化、iが50以下の間　iの値を1ずつ増やしながら出力し続ける。]
        */
        int i = 1;
        while(i <= 50) {
            System.out.print(i);
            i++;
        }
        System.out.println();


        /* ④ 行の最初に「段数」と「||」を追加したものを表示させるプログラムを作成しなさい。
        * 5 || 5 | 10 | 15 | 20 | 25 | 30 | 35 | 40 | 45 |
        * 6 || 6 | 12 | 18 | 24 | 30 | 36 | 42 | 48 | 54 |
        * 7 || 7 | 14 | 21 | 28 | 35 | 42 | 49 | 56 | 63 |
        * 8 || 8 | 16 | 24 | 32 | 40 | 48 | 56 | 64 | 72 |
        * 9 || 9 | 18 | 27 | 36 | 45 | 54 | 63 | 72 | 81 |
        */
       //5段から９段をループ
       for(int dan = 5;dan<=9;dan++){
        //行の先頭に「段数||」を表示
        System.out.print(dan+"||");

        //各段の計算結果(１から９までを掛ける)ループ
       for(int kakeru = 1;kakeru<=9;kakeru++){
        //計算結果と区切りを表示
        System.out.print(dan*kakeru);
        //最後の要素以外は"|"を表示
       if(kakeru<=9){
        System.out.print("|");
         }   
        }
        //一つの段が終わったら改行
       System.out.println();
       }
    }
}