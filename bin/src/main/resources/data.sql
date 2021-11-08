
INSERT INTO CLIENT_ENTITY (ID, CREATED, UPDATED, ADRESS, CPF, EMAIL, NAME, RG, SEX, TELEPHONE_NUMBER) VALUES
  (1, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP, 'Rua Ângela Maria', '011.227.374-24', 'leandrovicentebentogomes..leandrovicentebentogomes@tonyveiculos.com.br', 'Leandro Vicente Bento Gomes', '16.725.538-1', 'Masculino', '(21) 99530-4975'),
  (2, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP, 'Rua José Xavier', '483.623.462-05', 'heloisejennifervalentinabaptista_@keffin.com.br', 'Heloise Jennifer Valentina Baptista', '11.276.903-2', 'Feminino', '(84) 98207-2915'),
  (3, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP, 'Quadra Mocambinho - Setor A', '355.601.923-09', 'llarissaheloisafigueiredo@tasaut.com.br', 'Larissa Heloisa Figueiredo', '35.033.035-9', 'Feminino', ''),
  (4, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP, 'Rua Barcelona', '974.545.658-67', 'vitoryagoyurialves..vitoryagoyurialves@huios.com.br', 'Vitor Yago Yuri Alves', 'Rua Barcelona', 'Masculino', '(84) 98745-8047'),
  (5, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP, 'Rua Anna Groh', '193.845.976-86', 'elainedanielaferreira__elainedanielaferreira@ficopola.net', 'Elaine Daniela Ferreira', '23.864.611-7', 'Feminino', '(47) 99238-1665');

INSERT INTO AGENCY_ENTITY (ID, CREATED, UPDATED, CODE, NAME) VALUES
  (1, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP, 1368L , 'Agência Butiá-RS '),
  (2, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP, 1679L, 'Agência São Paulo-SP');

INSERT INTO ACCOUNT_ENTITY (ID, CREATED, UPDATED, BALANCE, LIMITE, NUMBER, AGENCY_ID, CLIENT_ID) VALUES
  (1, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP, 1000,3000,'35691226-3',1,1),
  (2, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP, 8000,10000,'39571-6',1,2),
  (3, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP, 3000,5000,'1163041-3',2,3),
  (4, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP, 10000,15000,'493069-0',2,4),
  (5, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP, 100,2000,'90788-0',1,5),
  (6, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP, 3000,8000,'35577444-6',2,5);


