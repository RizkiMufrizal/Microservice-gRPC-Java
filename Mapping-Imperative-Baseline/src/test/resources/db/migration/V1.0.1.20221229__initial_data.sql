INSERT INTO tb_end_point(id, backend, backend_function, url, method, timeout, connect_timeout)
VALUES ('8b021d4c-43cc-42d1-93b6-b91375e2d818', 'BE', 'sayHello', 'http://localhost:8181/simulator/sayHello', 'POST',
        30000, 30000);

INSERT INTO tb_harmonized(id, backend, backend_code, backend_desc, harmonized_code, harmonized_desc,
                          harmonized_http_status, is_error)
VALUES ('d1ab0156-dd3b-46d1-9ac8-b91b730ec2b6', 'BE', '200', 'Success', '00', 'Success', 200, false);

INSERT INTO tb_system_parameter(param_name, description, param_value)
VALUES ('BACKEND_KEY', 'Backend Key', '12345');