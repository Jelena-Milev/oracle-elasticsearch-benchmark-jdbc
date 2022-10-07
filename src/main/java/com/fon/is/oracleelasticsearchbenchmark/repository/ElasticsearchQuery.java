package com.fon.is.oracleelasticsearchbenchmark.repository;

public class ElasticsearchQuery {

    //---------------------- SALES ----------------------

    public static final String SELECT_SALES_PERCENT_1 = 
            "SELECT * FROM sales_plain WHERE id < 5001";
    
    public static final String SELECT_SALES_PERCENT_5 = 
            "SELECT * FROM sales_plain WHERE id < 25001";
    
    public static final String SELECT_SALES_PERCENT_10 = 
            "SELECT * FROM sales_plain WHERE id < 50001";
    
    public static final String SELECT_SALES_PERCENT_25 = 
            "SELECT * FROM sales_plain WHERE id < 125001";
    
    public static final String SELECT_SALES_PERCENT_50 = 
            "SELECT * FROM sales_plain WHERE id < 250001";
    
    public static final String SELECT_SALES_PERCENT_75 = 
            "SELECT * FROM sales_plain WHERE id < 375001";
    
    public static final String SELECT_SALES_PERCENT_100 = 
            "SELECT * FROM sales_plain";

    public static final String SELECT_SALES_FOR_NORDIC_COUNTRIES_UNOPTIMIZED =
            "select * from sales_plain where \n" +
                    "country = 'Norway' \n" +
                    "or country = 'Sweeden'\n" +
                    "or country = 'Denmark'\n" +
                    "or country = 'Finland'";

    public static final String SELECT_SALES_JUNE_2015_OPTIMIZED =
            "SELECT * FROM sales_plain WHERE " +
                    " order_date > '2014-12-31' AND order_date < '2015-01-08'";

    //---------------------- COMPANIES ----------------------

    //select * from company_plain
    public static final String SELECT_MIDDLE_SIZED_COMPANIES_IN_USA =
            "SELECT * FROM company_plain WHERE country = 'USA' AND company_size = 'Medium'";

    //iz nekog razloga ne radi
//    public static final String SELECT_LTD_COMPANIES =
//            "select * from company_plain where company_name like '%ltd%'";

    //---------------------- BOTH TABLES ----------------------

    //line 1:30: IN query not supported yet
//    public static final String SELECT_COMPANIES_WITHOUT_SALES_UNOPTIMIZED =
//            "SELECT * FROM company WHERE id NOT IN " +
//                    "(select company_id from sales)";

    //line 1:33: EXISTS is not yet supported
//    public static final String SELECT_COMPANIES_WITHOUT_SALES_OPTIMIZED =
//            "SELECT * FROM company WHERE NOT EXISTS " +
//                    "(SELECT company_id FROM sales " +
//                    "WHERE sales.company_id = company.id)";

    //Queries with JOIN are not yet supported
//    public static final String SELECT_COMPANIES_WITH_SALES_UNOPTIMIZED =
//            "SELECT DISTINCT company.* " +
//                    "FROM company JOIN sales ON company.id = sales.company_id";

    //line 1:29: EXISTS is not yet supported
//    public static final String SELECT_COMPANIES_WITH_SALES_OPTIMIZED =
//            "SELECT * FROM company WHERE EXISTS (SELECT company_id FROM sales)";

    //line 1:30: Queries with JOIN are not yet supported
//    public static final String SELECT_COMPANIES_THAT_SOLD_FRUITS_TO_SERBIA =
//            "SELECT company.* FROM sales JOIN company on sales.company_id = company.id " +
//                    "WHERE sales.country = 'Serbia' AND sales.item_type = 'Fruits'";

    //line 1:30: Queries with JOIN are not yet supported
//    public static final String SELECT_COMPANIES_SOLD_OVER_4000_UNITS_PER_ITEM_TYPE_IN_MONTH =
//            "select company.id, company.company_name, sales.item_type, sum(sales.units_sold) as \"Total_units_sold\" " +
//                    "from sales join company on company.id = sales.company_id " +
//                    "where sales.order_date >= '1.1.2015.' and sales.order_date <= '31.1.2015.' " +
//                    "group by company.id, company.company_name, sales.item_type " +
//                    "having sum(sales.units_sold) > 4000";
}
