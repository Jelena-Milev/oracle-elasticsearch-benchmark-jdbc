package com.fon.is.oracleelasticsearchbenchmark.repository;

public class OracleQuery {

    //---------------------- SALES ----------------------

    public static final String SELECT_SALES_PERCENT_1 =
            "SELECT * FROM sales WHERE id < 5001";

    public static final String SELECT_SALES_PERCENT_5 =
            "SELECT * FROM sales WHERE id < 25001";

    public static final String SELECT_SALES_PERCENT_10 =
            "SELECT * FROM sales WHERE id < 50001";

    public static final String SELECT_SALES_PERCENT_25 =
            "SELECT * FROM sales WHERE id < 125001";

    public static final String SELECT_SALES_PERCENT_50 =
            "SELECT * FROM sales WHERE id < 250001";

    public static final String SELECT_SALES_PERCENT_75 =
            "SELECT * FROM sales WHERE id < 375001";

    public static final String SELECT_SALES_PERCENT_100 =
            "SELECT * FROM sales";

    public static final String SELECT_SALES_FOR_NORDIC_COUNTRIES =
            "select * from sales where \n" +
                    "country = 'Norway' \n" +
                    "or country = 'Sweeden'\n" +
                    "or country = 'Denmark'\n" +
                    "or country = 'Finland'";

    public static final String SELECT_SALES_FOR_NORDIC_COUNTRIES_OPTIMIZED =
            "select * from sales where country = 'Norway'\n" +
                    "union all\n" +
                    "select * from sales_100k where country = 'Sweeden'\n" +
                    "union all\n" +
                    "select * from sales_100k where country = 'Denmark'\n" +
                    "union all\n" +
                    "select * from sales_100k where country = 'Finland'";

    // ovo ne koristi indeks za sales ali koristi za sales
    // previse je rezultata za jedan mesec u 500k
    // vidi kakva je raspodela za shipping_date
    public static final String SELECT_SALES_JUNE_2015_UNOPTIMIZED =
            "SELECT * FROM sales WHERE " +
                    "EXTRACT(YEAR FROM ORDER_DATE)=2015 " +
                    "AND EXTRACT(MONTH FROM ORDER_DATE)=1 " +
                    "AND EXTRACT(DAY FROM ORDER_DATE)>=1 " +
                    "AND EXTRACT(DAY FROM ORDER_DATE)<=7";

    public static final String SELECT_SALES_JUNE_2015_OPTIMIZED =
            "SELECT * FROM sales WHERE " +
                    "order_date > '31.12.2014' AND order_date < '8.1.2015'";

    //---------------------- COMPANIES ----------------------

    // optimized with indexes
    public static final String SELECT_MIDDLE_SIZED_COMPANIES_IN_USA =
            "SELECT * FROM companies WHERE country = 'USA' AND company_size = 'Medium'";

    public static final String SELECT_LTD_COMPANIES =
            "select * from company where company_name like '%Ltd%'";

    //---------------------- BOTH TABLES ----------------------
    public static final String SELECT_COMPANIES_WITH_SALES =
            "SELECT * FROM companies WHERE EXISTS " +
                    "(SELECT company_id FROM sales " +
                    "WHERE sales.company_id = companies.id)";

    // optimized with indexes
    public static final String SELECT_COMPANIES_THAT_SOLD_FRUITS_TO_SERBIA =
            "SELECT company.* FROM sales JOIN company on sales.company_id = company.id " +
                    "WHERE sales.country = 'Serbia' AND sales.item_type = 'Fruits'";

    public static final String SELECT_COMPANIES_SOLD_OVER_4000_UNITS_PER_ITEM_TYPE_IN_MONTH =
            "select company.id, company.company_name, sales.item_type, sum(sales.units_sold) as \"Total_units_sold\" " +
                    "from sales join company on company.id = sales.company_id " +
                    "where sales.order_date >= '1.1.2015.' and sales.order_date <= '07.1.2015.' " +
                    "group by company.id, company.company_name, sales.item_type " +
                    "having sum(sales.units_sold) > 4000";
}
