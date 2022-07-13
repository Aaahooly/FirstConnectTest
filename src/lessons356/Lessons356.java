package lessons356;


import java.util.Arrays;

public class Lessons356 {
    public static void main(String[] args) {
        byte[] massive = {61,62,63,64,65,66,67,68,69};
        AsciiCharSequence oms = new AsciiCharSequence(massive);
        System.out.println(oms.length());
        System.out.println(oms.charAt(1));
        System.out.println(oms.subSequence(1,5));


    }
        public static class AsciiCharSequence implements java.lang.CharSequence {
            private byte[] bytes;

            public AsciiCharSequence(byte[] i) {
                this.bytes = i;
            }

            @Override
            public int length() {
                return this.bytes.length;
            }

            @Override
            public char charAt(int index) {
                return (char) this.bytes[index];
            }

            @Override
            public AsciiCharSequence subSequence(int start, int end) {
                AsciiCharSequence interception = new AsciiCharSequence(this.bytes);
                interception.bytes = Arrays.copyOfRange(this.bytes, start, end);
                return interception;
            }

            @Override
            public String toString() {
                StringBuilder stringBuilder = new StringBuilder();
                for (int i = 0; i < this.bytes.length; i++) {
                    stringBuilder.append((char) this.bytes[i]);
                }
                return stringBuilder.toString();
            }
        }
    }


