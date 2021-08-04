package org.max.training;

public class Rexexp {


    public static String findNumberOfVidgukiv(String a) {
        String[] words = a.split("\n");
        String b="0";
        for (int i = 0; i < words.length; i++) {
            if (words[i].endsWith("відгуків") || words[i].endsWith("відгуки")|| (words[i].endsWith("відгук"))&&(!words[i].startsWith("Залишити"))) {b=words[i];break;}};
        String[] words1 = b.split(" ");
        b=words1[0];
        return b;}
        public static void main (String[]args){

            String a = "−4%\n" +
                    "Холодильник BOSCH KGN36NL306\n" +
                    "26 відгуків\n" +
                    "13 799 ₴\n" +
                    "\n" +
                    "13 299₴\n" +
                    "\n" +
                    "Є в наявності";

            String c=findNumberOfVidgukiv(a);
            System.out.println(c);

        }
}