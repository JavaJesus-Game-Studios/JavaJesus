package ca.javajesus.saves;

public class Convert
{
    public String strToBinary(String inputString){

        int[] ASCIIHolder = new int[inputString.length()];

        //Storing ASCII representation of characters in array of ints
        for(int index = 0; index < inputString.length(); index++){
            ASCIIHolder[index] = (int)inputString.charAt(index);
        }

        StringBuffer binaryStringBuffer = new StringBuffer();

        /* Now appending values of ASCIIHolder to binaryStringBuffer using
         * Integer.toBinaryString in a for loop. Should not get an out of bounds
         * exception because more than 1 element will be added to StringBuffer
         * each iteration.
         */
        for(int index =0;index <inputString.length();index ++){

            binaryStringBuffer.append(Integer.toBinaryString
                    (ASCIIHolder[index]));
        }

        String binaryToBeReturned = binaryStringBuffer.toString();

        binaryToBeReturned.replace(" ", "");

        return binaryToBeReturned;
    }

    public String binaryToString(String binaryString){
        String returnString = "";
        int charCode;
        for(int i = 0; i < binaryString.length(); i+=7)
        {
        charCode = Integer.parseInt(binaryString.substring(i, i+7), 2);
        String returnChar = new Character((char)charCode).toString();
        returnString += returnChar;
        }
        return returnString;
    }
}
