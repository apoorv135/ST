package com.Hotel.Reservation;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;


public class HRTest {

    private MainScreen mainscreen;

    @Before
    public void setUp()
    {
        mainscreen = new MainScreen();

    }

    @Test
    public void testluxuryfull()
    {
        Customer c = new Customer();
        Book b = new Book();
        Luxury l1 = new Luxury();
        Deluxe d = new Deluxe();
        SuperDeluxe sd = new SuperDeluxe();
        Laundry l[][][]=new Laundry[10][5][5];
        Transportation t[][][]=new Transportation[10][5][5];
//        Transportation t = new Transportation();
//        Laundry la = new Laundry();
        l1.statuschange();
        int i, j, k;
        for(i=0;i<10;i++)
        {
            for(j=0;j<5;j++)
            {
                for(k=0;k<5;k++)
                {
                    t[i][j][k]=new Transportation();
                    l[i][j][k]=new Laundry();

                }
            }
        }
        c.no = 1;
        c.ld = 1;
        c.d = 1;
        c.name = "abc";
        c.bookingno = "0lx1";
        c.status = true;
        c.type = 1;
        c.nod = 1;

        b.bookno = "lx1";
        b.ff = 1500;
        b.s1 = 1;

        int ily = 1, flag1 = 0, idx = 1, flag2 = 0, isdx = 1, flag3 = 0;



        String message = "No Luxury Rooms Available";
        Assert.assertEquals(message,b.BookNew(c,l1,d,sd,t,l,ily,flag1,idx,flag2,isdx,flag3));

    }

    @Test
    public void testdeluxefull()
    {
        Customer c = new Customer();
        Book b = new Book();
        Luxury l1 = new Luxury();
        Deluxe d = new Deluxe();
        SuperDeluxe sd = new SuperDeluxe();
        Laundry l[][][]=new Laundry[10][5][5];
        Transportation t[][][]=new Transportation[10][5][5];
//        Transportation t = new Transportation();
//        Laundry la = new Laundry();
        d.statuschange();

        int i, j, k;
        for(i=0;i<10;i++)
        {
            for(j=0;j<5;j++)
            {
                for(k=0;k<5;k++)
                {
                    t[i][j][k]=new Transportation();
                    l[i][j][k]=new Laundry();

                }
            }
        }
        c.no = 1;
        c.ld = 1;
        c.d = 1;
        c.name = "abc";
        c.bookingno = "0dx1";
        c.status = true;
        c.type = 2;
        c.nod = 1;

        b.bookno = "dx1";
        b.ff = 2000;

        int ily = 1, flag1 = 0, idx = 1, flag2 = 0, isdx = 1, flag3 = 0;



        String message = "No Deluxe Rooms Available";
        Assert.assertEquals(message,b.BookNew(c,l1,d,sd,t,l,ily,flag1,idx,flag2,isdx,flag3));

    }

    @Test
    public void testsuperdeluxefull()
    {
        Customer c = new Customer();
        Book b = new Book();
        Luxury l1 = new Luxury();
        Deluxe d = new Deluxe();
        SuperDeluxe sd = new SuperDeluxe();
        Laundry l[][][]=new Laundry[10][5][5];
        Transportation t[][][]=new Transportation[10][5][5];
//        Transportation t = new Transportation();
//        Laundry la = new Laundry();
        sd.statuschange();

        int i, j, k;
        for(i=0;i<10;i++)
        {
            for(j=0;j<5;j++)
            {
                for(k=0;k<5;k++)
                {
                    t[i][j][k]=new Transportation();
                    l[i][j][k]=new Laundry();

                }
            }
        }
        c.no = 1;
        c.ld = 1;
        c.d = 1;
        c.name = "abc";
        c.bookingno = "0sdx1";
        c.status = true;
        c.type = 3;
        c.nod = 1;

        b.bookno = "sdx1";
        b.ff = 2500;

        int ily = 1, flag1 = 0, idx = 1, flag2 = 0, isdx = 1, flag3 = 0;



        String message = "No Super Deluxe Rooms Available";
        Assert.assertEquals(message,b.BookNew(c,l1,d,sd,t,l,ily,flag1,idx,flag2,isdx,flag3));

    }

    @Test
    public void testluxury()
    {
        Customer c = new Customer();
        Book b = new Book();
        Luxury l1 = new Luxury();
        Deluxe d = new Deluxe();
        SuperDeluxe sd = new SuperDeluxe();
        Laundry l[][][]=new Laundry[10][5][5];
        Transportation t[][][]=new Transportation[10][5][5];
//        Transportation t = new Transportation();
//        Laundry la = new Laundry();
        l1.statuschange();

        int i, j, k;
        for(i=0;i<10;i++)
        {
            for(j=0;j<5;j++)
            {
                for(k=0;k<5;k++)
                {
                    t[i][j][k]=new Transportation();
                    l[i][j][k]=new Laundry();

                }
            }
        }
        c.no = 1;
        c.ld = 1;
        c.d = 1;
        c.name = "abc";
        c.bookingno = "0lx1";
        c.status = true;
        c.type = 1;
        c.nod = 1;

        b.bookno = "lx1";
        b.ff = 1500;

        l1.rate = 1500;

        int ily = 1, flag1 = 1, idx = 1, flag2 = 0, isdx = 1, flag3 = 0;



        String message = "Rooms Available";
        Assert.assertEquals(message,b.BookNew(c,l1,d,sd,t,l,ily,flag1,idx,flag2,isdx,flag3));

    }

    @Test
    public void testdeluxe()
    {
        Customer c = new Customer();
        Book b = new Book();
        Luxury l1 = new Luxury();
        Deluxe d = new Deluxe();
        SuperDeluxe sd = new SuperDeluxe();
        Laundry l[][][]=new Laundry[10][5][5];
        Transportation t[][][]=new Transportation[10][5][5];
//        Transportation t = new Transportation();
//        Laundry la = new Laundry();
        d.statuschange();

        int i, j, k;
        for(i=0;i<10;i++)
        {
            for(j=0;j<5;j++)
            {
                for(k=0;k<5;k++)
                {
                    t[i][j][k]=new Transportation();
                    l[i][j][k]=new Laundry();

                }
            }
        }
        c.no = 1;
        c.ld = 1;
        c.d = 1;
        c.name = "abc";
        c.bookingno = "0dx1";
        c.status = true;
        c.type = 2;
        c.nod = 1;

        b.bookno = "dx1";
        b.ff = 2000;

        d.rate = 2000;

        int ily = 1, flag1 = 0, idx = 1, flag2 = 1, isdx = 1, flag3 = 0;



        String message = "Rooms Available";
        Assert.assertEquals(message,b.BookNew(c,l1,d,sd,t,l,ily,flag1,idx,flag2,isdx,flag3));

    }

    @Test
    public void testsuperdeluxe()
    {
        Customer c = new Customer();
        Book b = new Book();
        Luxury l1 = new Luxury();
        Deluxe d = new Deluxe();
        SuperDeluxe sd = new SuperDeluxe();
        Laundry l[][][]=new Laundry[10][5][5];
        Transportation t[][][]=new Transportation[10][5][5];
//        Transportation t = new Transportation();
//        Laundry la = new Laundry();
        sd.statuschange();

        int i, j, k;
        for(i=0;i<10;i++)
        {
            for(j=0;j<5;j++)
            {
                for(k=0;k<5;k++)
                {
                    t[i][j][k]=new Transportation();
                    l[i][j][k]=new Laundry();

                }
            }
        }
        c.no = 1;
        c.ld = 1;
        c.d = 1;
        c.name = "abc";
        c.bookingno = "0sdx1";
        c.status = true;
        c.type = 3;
        c.nod = 1;

        b.bookno = "sdx1";
        b.ff = 2500;

        sd.rate = 2500;

        int ily = 1, flag1 = 0, idx = 1, flag2 = 0, isdx = 1, flag3 = 1;



        String message = "Rooms Available";
        Assert.assertEquals(message,b.BookNew(c,l1,d,sd,t,l,ily,flag1,idx,flag2,isdx,flag3));

    }

    @Test
    public void testLaundryCost1()
    {
        Laundry ly = new Laundry();
        ly.bno = "1lx1";
        ly.type = 1;
        ly.quantity = 1;

        int expectedoutput = 100;
        Assert.assertEquals(expectedoutput, ly.getTotalCost());
    }

    @Test
    public void testLaundryCost2()
    {
        Laundry ly = new Laundry();
        ly.bno = "1lx1";
        ly.type = 2;
        ly.quantity = 2;

        int expectedoutput = 400;
        Assert.assertEquals(expectedoutput, ly.getTotalCost());
    }

    @Test
    public void testLaundryCost3()
    {
        Laundry ly = new Laundry();
        ly.bno = "1lx1";
        ly.type = 3;
        ly.quantity = 2;

        int expectedoutput = 600;
        Assert.assertEquals(expectedoutput, ly.getTotalCost());
    }

    @Test
    public void testTransportationCost1()
    {
        Transportation t = new Transportation();
        t.bno = "1lx1";
        t.type = 1;
        t.quantity = 1;

        int expectedoutput = 100;
        Assert.assertEquals(expectedoutput, t.getTotalCost());
    }

    @Test
    public void testTransportationCost2()
    {
        Transportation t = new Transportation();
        t.bno = "1lx1";
        t.type = 2;
        t.quantity = 2;

        int expectedoutput = 400;
        Assert.assertEquals(expectedoutput, t.getTotalCost());
    }

    @Test
    public void testTransportationCost3()
    {
        Transportation t = new Transportation();
        t.bno = "0lx1";
        t.type = 3;
        t.quantity = 2;

        int expectedoutput = 600;
        Assert.assertEquals(expectedoutput, t.getTotalCost());
    }


}
