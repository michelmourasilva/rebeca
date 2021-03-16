create or replace view CO.customer_order_products as 
  select o.order_id, o.order_datetime, o.order_status, 
         c.customer_id, c.email_address, c.full_name, 
         sum ( oi.quantity * oi.unit_price ) order_total,
         listagg (
           p.product_name, ', ' 
         ) within group ( order by oi.line_item_id ) items
  from   CO.orders o
  join   CO.order_items oi
  on     o.order_id = oi.order_id
  join   CO.customers c
  on     o.customer_id = c.customer_id
  join   CO.products p
  on     oi.product_id = p.product_id
  group  by o.order_id, o.order_datetime, o.order_status, 
         c.customer_id, c.email_address, c.full_name;
   
create or replace view CO.store_orders as 
  select case
           grouping_id ( store_name, order_status ) 
           when 1 then 'STORE TOTAL'
           when 2 then 'STATUS TOTAL'
           when 3 then 'GRAND TOTAL'
         end total,
         s.store_name, 
         coalesce ( s.web_address, s.physical_address ) address,
         s.latitude, s.longitude,
         o.order_status,
         count ( distinct o.order_id ) order_count,
         sum ( oi.quantity * oi.unit_price ) total_sales
  from   stores s
  join   orders o
  on     s.store_id = o.store_id
  join   order_items oi
  on     o.order_id = oi.order_id
  group  by grouping sets ( 
    ( s.store_name, coalesce ( s.web_address, s.physical_address ), s.latitude, s.longitude ),
    ( s.store_name, coalesce ( s.web_address, s.physical_address ), s.latitude, s.longitude, o.order_status ),
    o.order_status, 
    ()
  );
  
/* A relational view of the reviews stored in the JSON for each product */

create or replace view CO.product_reviews as   
  select p.product_name, r.rating, 
         round ( 
           avg ( r.rating ) over (
             partition by product_name
           ),
           2
         ) avg_rating,
         r.review
  from   products p,
         json_table (
           p.product_details, '$'
           columns ( 
             nested path '$.reviews[*]'
             columns (
               rating integer path '$.rating',
               review varchar2(4000) path '$.review'
             )
           )
         ) r;
         
create or replace view CO.product_orders as 
  select p.product_name, o.order_status, 
         sum ( oi.quantity * oi.unit_price ) total_sales,
         count (*) order_count
  from   orders o
  join   order_items oi
  on     o.order_id = oi.order_id
  join   customers c
  on     o.customer_id = c.customer_id
  join   products p
  on     oi.product_id = p.product_id
  group  by p.product_name, o.order_status; 