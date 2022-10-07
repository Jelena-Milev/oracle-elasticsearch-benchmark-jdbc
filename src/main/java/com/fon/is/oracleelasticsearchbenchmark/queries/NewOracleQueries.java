package com.fon.is.oracleelasticsearchbenchmark.queries;

public class NewOracleQueries {

    public static final String NO_MATERIALIZED_VIEW =
            "SELECT c.id, c.name, ic.name, s.order_date, SUM(s.units_sold) " +
                    "FROM company c JOIN sale s ON c.id = s.company_id " +
                    "JOIN item_category ic ON s.item_category_id = ic.id " +
                    "WHERE s.order_date >= '1.1.2015.' AND s.order_date <= '07.1.2015.' " +
                    "GROUP BY c.id, c.name, ic.name, s.order_date " +
                    "HAVING sum(s.units_sold) > 4000 " +
                    "ORDER BY s.order_date ";

    public static final String WITH_MATERIALIZED_VIEW = "select * from companies_sold_item_category " +
            "where total_units_sold > 4000 AND order_date >= '1.1.2015.' AND order_date <= '07.1.2015.' " +
            "order by order_date ";

    public static final String BEFORE_DENORMALIZATION = "SELECT cp.id, cp.first_name, cp.last_name, cp.mail " +
            "FROM contact_person cp JOIN company c on c.contact_person_id = cp.id JOIN city ci ON c.city_id = ci.id JOIN country co ON ci.country_id = co.id " +
            "WHERE co.name = 'France' ";

    public static final String AFTER_DENORMALIZATION = "SELECT cp.id, cp.first_name, cp.last_name, cp.mail " +
            "FROM contact_person cp JOIN company c on c.contact_person_id = cp.id " +
            "WHERE country = 'France' ";

    public static final String BEFORE_LOGIC_OPTIMIZATION = "SELECT c.id, COUNT(*) " +
            "FROM sale s JOIN company c on s.company_id = c.id " +
            "WHERE c.company_size = 'Medium' " +
            "GROUP BY c.id " +
            "ORDER BY COUNT(*) DESC ";

    public static final String AFTER_LOGIC_OPTIMIZATION = "SELECT id, sales_count " +
            "FROM company " +
            "WHERE company_size = 'Medium' " +
            "ORDER BY sales_count DESC ";

    public static final String BEFORE_PARTITIONING = "SELECT * " +
            "FROM sale s JOIN sales_channel sc ON s.sales_channel_id = sc.id " +
            "WHERE s.order_priority = 'C' AND sc.name = 'Ecommerce' " +
            "ORDER BY order_date ";

    public static final String AFTER_PARTITIONING = "SELECT * " +
            "FROM part_sale s JOIN sales_channel sc ON s.sales_channel_id = sc.id " +
            "WHERE s.order_priority = 'C' AND sc.name = 'Ecommerce' " +
            "ORDER BY order_date ";

    public static final String BEFORE_BITMAP_INDEXING = "SELECT * FROM sale WHERE TO_CHAR(ship_date,'MM/DD/YYYY') = '08/23/2015' ORDER BY order_date ";
    public static final String AFTER_BITMAP_INDEXING = "SELECT * FROM sale WHERE ship_date = TO_DATE('8/23/2015', 'MM/DD/YYYY') ORDER BY order_date ";

    public static final String BEFORE_B_TREE_INDEXING = "SELECT company.* " +
            "FROM company LEFT JOIN sale ON sale.company_id = company.id " +
            "WHERE sale.id IS NULL";
    public static final String AFTER_B_TREE_INDEXING = "SELECT * FROM company WHERE NOT EXISTS (SELECT company_id FROM sale WHERE sale.company_id = company.id) ";
}