-- 계정 권한 부여
grant all on erp.* to 'user_erp'@'localhost'
identified by 'rootroot';

-- File 권한(backup,load) -- root만 권함 부여가능

grant File
on *.*
to 'user_erp'@'localhost';