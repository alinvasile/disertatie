CREATE OR REPLACE VIEW VW_ACTIVE_CAMPAIGNS AS
SELECT rownum as id ,
    c.campaign_name,
  to_char(c.campaign_to,   'DD-MM-YYYY HH24:MI') campaign_to,
  d.discount_percentage || ' %' discount_percentage,
  ROUND(TRUNC(campaign_to) -TRUNC(sysdate)) left_days,
  c.details
FROM campaign c,
campaign_details cd,
discount d
WHERE 
c.campaign_id = cd.campaign_id and
cd.discount_id = d.discount_id and
c.campaign_from <= sysdate
 AND c.campaign_to >= sysdate
ORDER BY left_days;

