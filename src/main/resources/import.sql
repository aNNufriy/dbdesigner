INSERT INTO public.sem_node_type VALUES ('c46dcf8a-7679-43d0-baa1-f87f606be11c', 'Products', NULL);

INSERT INTO public.sem_value_type_group VALUES ('ce32e30f-c305-43f0-8c23-29ced51f554f', 'Product parameters');
INSERT INTO public.sem_value_type_group VALUES ('6cd580e2-23f4-4b3b-ab84-dd3d2e51c1fe', 'Product package content');

INSERT INTO public.sem_node_type_sem_value_type_groups VALUES ('c46dcf8a-7679-43d0-baa1-f87f606be11c', 'ce32e30f-c305-43f0-8c23-29ced51f554f');
INSERT INTO public.sem_node_type_sem_value_type_groups VALUES ('c46dcf8a-7679-43d0-baa1-f87f606be11c', '6cd580e2-23f4-4b3b-ab84-dd3d2e51c1fe');

INSERT INTO public.classifier VALUES ('d56a8ab5-d11c-4c5b-813a-d998ca48d3cb', 'methods');
INSERT INTO public.classifier VALUES ('b52956cd-5b49-4e57-afb6-3761a97aaf10', 'levels');
INSERT INTO public.classifier VALUES ('52e8ecfc-9984-4c92-ad52-4a2d369005fc', 'complexity');

INSERT INTO public.classifier_value VALUES ('a09e03e3-93e4-4a9f-8fe1-2e53162154d0', '1', '1 m', 'd56a8ab5-d11c-4c5b-813a-d998ca48d3cb');
INSERT INTO public.classifier_value VALUES ('864c9c8c-8140-425f-beb3-bdedd0156368', '2', '2 m', 'd56a8ab5-d11c-4c5b-813a-d998ca48d3cb');
INSERT INTO public.classifier_value VALUES ('c0d77cee-c4e9-4aa3-b81a-dc9dff8e4f56', '3', '3 m', 'd56a8ab5-d11c-4c5b-813a-d998ca48d3cb');
INSERT INTO public.classifier_value VALUES ('5246051a-a4c8-4e1e-af18-2e9c1786aec8', '1', '1 l', 'b52956cd-5b49-4e57-afb6-3761a97aaf10');
INSERT INTO public.classifier_value VALUES ('58b19868-2f3a-4980-980e-c784aaa746a2', '2', '2 l', 'b52956cd-5b49-4e57-afb6-3761a97aaf10');
INSERT INTO public.classifier_value VALUES ('f179ad3f-438d-4262-818b-f67c0618c08b', '3', '3 l', 'b52956cd-5b49-4e57-afb6-3761a97aaf10');
INSERT INTO public.classifier_value VALUES ('9c375720-3d0b-45e4-9f1b-b1bc0feff03b', '1', 'A', '52e8ecfc-9984-4c92-ad52-4a2d369005fc');
INSERT INTO public.classifier_value VALUES ('9170aead-5bae-41f8-8545-b6e070ac0f1b', '2', 'B', '52e8ecfc-9984-4c92-ad52-4a2d369005fc');
INSERT INTO public.classifier_value VALUES ('9a285550-c19d-4dfc-802c-76f1d3e6e3da', '0', 'C', '52e8ecfc-9984-4c92-ad52-4a2d369005fc');

INSERT INTO public.files (id,description,md5hash,name,path,size) VALUES ('693de65f-edc2-412c-8fbe-08fa84eec1a4', NULL, '68b329da9893e34099c7d8ad5cb9c940', 'file 1', '2018-08-22/3361dd9d-32f2-415c-bf8d-618adeb06072.pdf', 1005632);
INSERT INTO public.files (id,description,md5hash,name,path,size) VALUES ('d489a832-0d87-410f-a7d9-68d5817f056f', NULL, '68b329da9893e34099c7d8ad5cb9c940', 'file 2', '2018-08-22/70608818-ca8c-4576-92b6-c231d3c40890.pdf', 3717428);

INSERT INTO public.node VALUES ('11765994-c89c-4b43-aa63-28c9163c852f', NULL, NULL, 'Product 1', 'c46dcf8a-7679-43d0-baa1-f87f606be11c');

INSERT INTO public.roles VALUES ('10ec56b2-33ec-46b3-9912-c56648f6acca', 'ADMIN',  NULL);
INSERT INTO public.roles VALUES ('e8712251-38f2-4b40-aa18-53a3caa8e1ec', 'EDITOR', NULL);
INSERT INTO public.roles VALUES ('56ba8d35-d255-4eb2-9d57-9b12b86b4d48', 'VIEWER', NULL);

INSERT INTO public.users VALUES ('4462273e-b364-4ac7-850d-a73c00dcf5fa', 'admin@testfield.ru', true, '$2a$04$RvSDLKcV4tBgzZ7V/nxmSeUnrH6LJ8XfxUX0mcJYsq/Ppp014yF8u', 'admin', NULL);
INSERT INTO public.users VALUES ('dfc0c7fd-e168-4795-920d-a1769775de3c', 'editor@testfield.ru', true, '$2a$04$Fsc2KC5LgI7LajHimR.F3uasKT19hOb2CZXAcwK4IGw61NjOpAZim', 'editor', NULL);

INSERT INTO public.users_roles VALUES ('4462273e-b364-4ac7-850d-a73c00dcf5fa', '10ec56b2-33ec-46b3-9912-c56648f6acca');
INSERT INTO public.users_roles VALUES ('dfc0c7fd-e168-4795-920d-a1769775de3c', 'e8712251-38f2-4b40-aa18-53a3caa8e1ec');

INSERT INTO public.sem_value_type (id,ordr,inner_type,name,required,single_value,classifier_id,sem_value_type_group_id) VALUES ('a6b6ca81-5478-425b-b91c-2c07c6a4b811', 1,'LONG', '#', false, true, NULL, 'ce32e30f-c305-43f0-8c23-29ced51f554f');
INSERT INTO public.sem_value_type (id,ordr,inner_type,name,required,single_value,classifier_id,sem_value_type_group_id) VALUES ('d8a14fba-7081-45f1-95d6-8e1eed1eddba', 2,'STRING', 'name', false, true, NULL, 'ce32e30f-c305-43f0-8c23-29ced51f554f');
INSERT INTO public.sem_value_type (id,ordr,inner_type,name,required,single_value,classifier_id,sem_value_type_group_id) VALUES ('c66d672d-1604-4399-9986-6c95bc377dd1', 3,'STRING', 'purpose', false, true, NULL, 'ce32e30f-c305-43f0-8c23-29ced51f554f');
INSERT INTO public.sem_value_type (id,ordr,inner_type,name,required,single_value,classifier_id,sem_value_type_group_id) VALUES ('446d37a8-9721-49fb-a953-ee914e906530', 4,'CLASSIFIER', 'methods', false, false, 'd56a8ab5-d11c-4c5b-813a-d998ca48d3cb', 'ce32e30f-c305-43f0-8c23-29ced51f554f');
INSERT INTO public.sem_value_type (id,ordr,inner_type,name,required,single_value,classifier_id,sem_value_type_group_id) VALUES ('84bf5867-2705-43e5-8bd3-8c2869aae127', 5,'CLASSIFIER', 'levels', false, false, 'b52956cd-5b49-4e57-afb6-3761a97aaf10', 'ce32e30f-c305-43f0-8c23-29ced51f554f');
INSERT INTO public.sem_value_type (id,ordr,inner_type,name,required,single_value,classifier_id,sem_value_type_group_id) VALUES ('19922065-cfc2-4055-8eb1-4fa5b92a0b98', 6,'STRING', 'task name', false, false, NULL, 'ce32e30f-c305-43f0-8c23-29ced51f554f');
INSERT INTO public.sem_value_type (id,ordr,inner_type,name,required,single_value,classifier_id,sem_value_type_group_id) VALUES ('9a2979aa-e899-46e2-999e-9398ef7d66b3', 7,'STRING', 'model name', false, false, NULL, 'ce32e30f-c305-43f0-8c23-29ced51f554f');
INSERT INTO public.sem_value_type (id,ordr,inner_type,name,required,single_value,classifier_id,sem_value_type_group_id) VALUES ('348f2604-edfa-4cf1-8c68-7d1835dce09b', 8,'STRING', 'owner', false, true, NULL, 'ce32e30f-c305-43f0-8c23-29ced51f554f');
INSERT INTO public.sem_value_type (id,ordr,inner_type,name,required,single_value,classifier_id,sem_value_type_group_id) VALUES ('b99b8e03-f380-4c92-b48b-fb878f74f136', 9,'STRING', 'suplyer', false, true, NULL, 'ce32e30f-c305-43f0-8c23-29ced51f554f');
INSERT INTO public.sem_value_type (id,ordr,inner_type,name,required,single_value,classifier_id,sem_value_type_group_id) VALUES ('76f334a1-d2ca-497c-a4d1-688c6ab041c7',10,'STRING', 'consumer', false, true, NULL, 'ce32e30f-c305-43f0-8c23-29ced51f554f');
INSERT INTO public.sem_value_type (id,ordr,inner_type,name,required,single_value,classifier_id,sem_value_type_group_id) VALUES ('03759ca4-dc3f-41de-b8b5-e3a4371664cd',11,'STRING', 'project', false, true, NULL, 'ce32e30f-c305-43f0-8c23-29ced51f554f');
INSERT INTO public.sem_value_type (id,ordr,inner_type,name,required,single_value,classifier_id,sem_value_type_group_id) VALUES ('cc868822-ecb2-4100-b119-135a642dabd5',12,'CLASSIFIER', 'complexity', false, true, '52e8ecfc-9984-4c92-ad52-4a2d369005fc', 'ce32e30f-c305-43f0-8c23-29ced51f554f');
INSERT INTO public.sem_value_type (id,ordr,inner_type,name,required,single_value,classifier_id,sem_value_type_group_id) VALUES ('b68cb6c2-e109-4bb0-ba93-a698d7fa68d4',13,'STRING', 'certificate', false, true, NULL, 'ce32e30f-c305-43f0-8c23-29ced51f554f');
INSERT INTO public.sem_value_type (id,ordr,inner_type,name,required,single_value,classifier_id,sem_value_type_group_id) VALUES ('5bf133e5-fa66-4794-8ea8-1aa4fed7db12',14,'STRING', 'operating system', false, false, NULL, 'ce32e30f-c305-43f0-8c23-29ced51f554f');
INSERT INTO public.sem_value_type (id,ordr,inner_type,name,required,single_value,classifier_id,sem_value_type_group_id) VALUES ('9940543d-022b-422c-a060-b8de82082a8a',15,'FILE', 'files', false, false, NULL, '6cd580e2-23f4-4b3b-ab84-dd3d2e51c1fe');

INSERT INTO public.value VALUES ('fe471d91-83bf-4550-8c38-c65932893343', NULL, NULL, NULL, NULL, 'f4205d67-913e-48ad-8824-21a0c287296d', '11765994-c89c-4b43-aa63-28c9163c852f', '9940543d-022b-422c-a060-b8de82082a8a');
INSERT INTO public.value VALUES ('9f902dff-ab27-4b85-905a-b2d1f0a238ad', NULL, NULL, NULL, NULL, '745ed8c3-4abf-4c1e-ac59-bf46a0a5ba9b', '11765994-c89c-4b43-aa63-28c9163c852f', '9940543d-022b-422c-a060-b8de82082a8a');
INSERT INTO public.value VALUES ('989d0f88-3675-40a0-b429-782eb35ebc73', NULL, NULL, 1, NULL, NULL, '11765994-c89c-4b43-aa63-28c9163c852f', 'a6b6ca81-5478-425b-b91c-2c07c6a4b811');
INSERT INTO public.value VALUES ('5864fcec-22e4-4646-9bbc-236a56cdf240', NULL, NULL, NULL, 'Product #1, PM-125-3', NULL, '11765994-c89c-4b43-aa63-28c9163c852f', 'd8a14fba-7081-45f1-95d6-8e1eed1eddba');
INSERT INTO public.value VALUES ('39c2aa57-a748-4743-a54b-91a1c1dad88f', NULL, NULL, NULL, NULL, 'c0d77cee-c4e9-4aa3-b81a-dc9dff8e4f56', '11765994-c89c-4b43-aa63-28c9163c852f', '446d37a8-9721-49fb-a953-ee914e906530');
INSERT INTO public.value VALUES ('7ec32e23-37ea-4ec0-b23b-8c1304f8e5f1', NULL, NULL, NULL, 'mm #1', NULL, '11765994-c89c-4b43-aa63-28c9163c852f', '9a2979aa-e899-46e2-999e-9398ef7d66b3');
INSERT INTO public.value VALUES ('f9eaca24-5e4d-45d5-8227-aa23831007b3', NULL, NULL, NULL, 'Project #1, 2013', NULL, '11765994-c89c-4b43-aa63-28c9163c852f', '03759ca4-dc3f-41de-b8b5-e3a4371664cd');
INSERT INTO public.value VALUES ('a3d5caa4-3894-4c2f-ab1c-aa38f5d10956', NULL, NULL, NULL, 'GG Corp', NULL, '11765994-c89c-4b43-aa63-28c9163c852f', '76f334a1-d2ca-497c-a4d1-688c6ab041c7');
INSERT INTO public.value VALUES ('1f1bd2ee-9d10-4451-9199-9a7e325f118b', NULL, NULL, NULL, 'task 1', NULL, '11765994-c89c-4b43-aa63-28c9163c852f', '19922065-cfc2-4055-8eb1-4fa5b92a0b98');
INSERT INTO public.value VALUES ('6b713946-3000-46f6-9183-db5702cb2531', NULL, NULL, NULL, NULL, '864c9c8c-8140-425f-beb3-bdedd0156368', '11765994-c89c-4b43-aa63-28c9163c852f', '446d37a8-9721-49fb-a953-ee914e906530');
INSERT INTO public.value VALUES ('71b3e552-cc30-4155-a810-913639c060d4', NULL, NULL, NULL, NULL, 'a09e03e3-93e4-4a9f-8fe1-2e53162154d0', '11765994-c89c-4b43-aa63-28c9163c852f', '446d37a8-9721-49fb-a953-ee914e906530');
INSERT INTO public.value VALUES ('9fdef820-cad9-49aa-aab6-4ee0ba64ed2f', NULL, NULL, NULL, NULL, '9c375720-3d0b-45e4-9f1b-b1bc0feff03b', '11765994-c89c-4b43-aa63-28c9163c852f', 'cc868822-ecb2-4100-b119-135a642dabd5');
INSERT INTO public.value VALUES ('3ca83155-bd7a-4c09-8562-f448d8807d55', NULL, NULL, NULL, NULL, 'f179ad3f-438d-4262-818b-f67c0618c08b', '11765994-c89c-4b43-aa63-28c9163c852f', '84bf5867-2705-43e5-8bd3-8c2869aae127');
INSERT INTO public.value VALUES ('947083c6-58ca-4327-a952-70721f7d0201', NULL, NULL, NULL, 'Windows 2000/NT/XP/7/8/10', NULL, '11765994-c89c-4b43-aa63-28c9163c852f', '5bf133e5-fa66-4794-8ea8-1aa4fed7db12');
INSERT INTO public.value VALUES ('3c1e0ce2-669a-48a2-a585-31bad016a01f', NULL, NULL, NULL, 'modeling', NULL, '11765994-c89c-4b43-aa63-28c9163c852f', 'c66d672d-1604-4399-9986-6c95bc377dd1');
INSERT INTO public.value VALUES ('c8c56161-9089-470a-bd1e-f92f9e9d606b', NULL, NULL, NULL, NULL, '58b19868-2f3a-4980-980e-c784aaa746a2', '11765994-c89c-4b43-aa63-28c9163c852f', '84bf5867-2705-43e5-8bd3-8c2869aae127');
INSERT INTO public.value VALUES ('3d16248b-d32e-41e7-85a6-56fb3ac291a0', NULL, NULL, NULL, NULL, '5246051a-a4c8-4e1e-af18-2e9c1786aec8', '11765994-c89c-4b43-aa63-28c9163c852f', '84bf5867-2705-43e5-8bd3-8c2869aae127');

INSERT INTO public.files VALUES ('f4205d67-913e-48ad-8824-21a0c287296d', NULL, '1deefe6649699946590856e901bbe5ff', '7z.exe', '2018-09-03/c4c6dc69-beba-4552-8342-2522df0c2bb5.exe', 1438086);
INSERT INTO public.files VALUES ('745ed8c3-4abf-4c1e-ac59-bf46a0a5ba9b', NULL, '47f4e5a9a573d7dd0d03a8b113e03dee', '7z.docx', '2018-09-03/5a7cee48-a504-4ebf-a20b-87ef75103150.docx', 16150);
