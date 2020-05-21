package it.unibo.oop.mge.parser;
import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.Test;
import java.util.*;

import it.unibo.oop.mge.model.BracketsParserImpl;

public class ParserTest {

    @Test
    public void rewriterTest() {
        List<String> listone = new ArrayList<>();
        String str1 = "ln(pow(x,2)*4+1)";
        String str2 = "x-1";
        String str3 = "(2+exp(x))+1";
        String str4 = "(x+1)*4";
        String str5 = "(1+1)*5";
        String str6 = "(3*(x+4)+1*(2+3))";
        String str7 = "3/(x+2)*((pow(2,x)))";
        String str8 = "ln(pow(x,2)*4)+2";
        String str9 = "ln(2+pow(x,2)*4)";
        String str10 = "ciaopippo";
        String str11 = "ln(2+pow(x,2)*4+pow(2,y))";
        String str12 = "pow(x,2+y)+5";
        String str13 = "pi*x";
        String str14 = "2x";
        String str15 = "1-abs(x+y)-abs(y-x)";
        String str16 = "1-pow(y-1,x)";
        String str17 = "pow(-1+y,x)";
        String str18 = "-1+x";
        String str19 = "2.19";
        String str20 = "pow(x,2+y)";
        String str21 = "abs(x)";
        String str22 = "(0.75)/exp(pow((x*5),2)*pow((y*5),2))";
        String str23 = "sin(10*(pow(x,2)+pow(y,2)))/10";
        String str24 = "sin(2*5)/10";
        String str25 = "abs(sin(x))+5*exp(pow(-x,100))*cos(x)";

        listone.add(str1);
        listone.add(str2);
        listone.add(str3);
        listone.add(str4);
        listone.add(str5);
        listone.add(str6);
        listone.add(str7);
        listone.add(str8);
        listone.add(str9);
        // listone.add(str10);
        listone.add(str11);
        listone.add(str12);
        listone.add(str13);
        listone.add(str15);
        listone.add(str16);
        listone.add(str17);
        listone.add(str18);
        listone.add(str19);
        listone.add(str20);
        listone.add(str21);
        listone.add(str22);
        listone.add(str23);
        listone.add(str24);
        listone.add(str25);

        // listone.forEach(i->{
        // FunctionParser.parse(i);
        // });
        //assertEquals(new BracketsParserImpl(str1).resolveBrackets(), "ln(sum(mul(pow(x,2),4),1))");
        assertEquals(new BracketsParserImpl(str2).resolveBrackets(), "sot(x,1)");
        assertEquals(new BracketsParserImpl(str4).resolveBrackets(), "mul(sum(x,1),4)");
        assertEquals(new BracketsParserImpl(str18).resolveBrackets(), "sum(sot(0,1),x)");
    }

}

