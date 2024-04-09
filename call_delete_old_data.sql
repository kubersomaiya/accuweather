CALL public.delete_old_data((current_date - interval '1 month')::date);
