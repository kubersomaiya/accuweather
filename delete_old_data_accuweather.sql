-- PROCEDURE: public.delete_old_data(date)

-- DROP PROCEDURE IF EXISTS public.delete_old_data(date);

CREATE OR REPLACE PROCEDURE public.delete_old_data(
	IN date_param date)
LANGUAGE 'plpgsql'
AS $BODY$
BEGIN
    DELETE FROM weather_forecast_details
    WHERE date < date_param;
END;
$BODY$;
ALTER PROCEDURE public.delete_old_data(date)
    OWNER TO postgres;
