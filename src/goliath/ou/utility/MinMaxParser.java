/*
 * The MIT License
 *
 * Copyright 2017 Ty Young.
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in
 * all copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN
 * THE SOFTWARE.
 */
package goliath.ou.utility;

public class MinMaxParser
{
    private char[] chars;
    private final int min;
    private final int max;
    
    public MinMaxParser(String str, int ignoreMin, int ignoreMax)
    {
        chars = str.toCharArray();
        
        for(int i = 0; i < ignoreMin; i++)
            findValue();
         
        min = findValue();
         
        for(int i = 0; i < ignoreMax; i++)
            findValue();
         
         max = findValue();
    }
    private int findValue()
    {
        String num = "";
        boolean foundAValue = false;
        for(int i = 0; i < chars.length; i++)
        {
            if(Character.isDigit(chars[i]))
            {
                num += chars[i];
                foundAValue = true;
            }
            else if(!Character.isDigit(chars[i]) && foundAValue)
            {
                if(chars[i-num.length()-1] == '-')
                    num = '-' + num;
                
                chars = new String(chars, i, chars.length-i).toCharArray();
                break;
            }
        }
        
        return Integer.parseInt(num);
    }
    public int getMin()
    {
        return min;
    }
    public int getMax()
    {
        return max;
    }
}
