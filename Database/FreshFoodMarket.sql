USE [master]
GO

IF EXISTS (SELECT * FROM sys.databases WHERE name = 'FreshFoodMarket')
	DROP DATABASE FreshFoodMarket
GO

Create database FreshFoodMarket

go
use FreshFoodMarket
go

Create table Account (
	accID int primary key identity(1, 1),
	username varchar(50) unique,
	password varchar(100) not null check(len(password) >= 8),
	role tinyint not null
)
go

Create table Employee (
	emID varchar(10) primary key,
	accID int foreign key references Account(accID),
	emName nvarchar(50),
	gender bit,
	email varchar(50) not null,
	emPhone varchar(20) not null,
	emAddress nvarchar(100),
	emImage nvarchar(255)
)
go

Create table Customer (
	cusID varchar(10) primary key,
	accID int foreign key references Account(accID),
	cusName nvarchar(50),
	gender bit,
	email varchar(50) not null,
	cusPhone varchar(20) not null,
	cusAddress nvarchar(100),
	cusImage nvarchar(255),
	updateBy nvarchar(50),
	updateDate date default getDate()
)
go

Create table Category (
	cateID varchar(5) primary key,
	cateName nvarchar(50)
)
go

Create table Product (
	proID varchar(10) primary key,
	cateID varchar(5) foreign key references Category(cateID),
	proName nvarchar(100),
	price money check(price>=0),
	[type] nvarchar(100),
	quantity int check(quantity>=0),
	image nvarchar(100),
	description nvarchar(max),
	rated tinyint,
	updateDate dateTime	default getdate()
)
go

Create table [Order] (
	orderID varchar(10) primary key,
	cusID varchar(10) foreign key references Customer(cusID),
	cusName nvarchar(50) not null,
	email varchar(50) not null,
	cusPhone varchar(20) not null,
	cusAddress nvarchar(100) not null,
	dateCreate dateTime default getdate(),
	total money,
	status int
)
go

Create table OrderDetail (
	proID varchar(10) foreign key references Product(proID),
	orderID varchar(10) foreign key references [Order](orderID), 
	quantity int, 
	price money, 
	total money,
	primary key (proID, orderID)
)
go

Create table Cart (
	cartID int primary key identity(1, 1),
	cusID varchar(10) foreign key references Customer(cusID) 
)
go

Create table CartDetail (
	cartID int foreign key references Cart(cartID),
	proID varchar(10) foreign key references Product(proID),
	quantity int,
	primary key (cartID, proID)
)
go

Create table Feedback (
	feID int primary key  identity(1, 1),
	cusID varchar(10) foreign key references Customer(cusID),
	proID varchar(10) foreign key references Product(proID),
	rated tinyint,
	content nvarchar(max),
	dateCreate dateTime default getdate()
)

Create table Post (
	postID int primary key identity(1, 1),
	thumbnail nvarchar(255),
	title nvarchar(255),
	description nvarchar(max),
	author nvarchar(50),
	flag bit,
	status bit
)
go

Create table PostList (
	postID int foreign key references Post(postID),
	proID varchar(10) foreign key references Product(proID),
	primary key (proID, postID)
)
go

Create table Slider (
	slideID varchar(10) primary key,
	slideImage nvarchar(255),
	title nvarchar(255),
	description nvarchar(255),
	backlink varchar(255),
	status bit,
	notes nvarchar(255)
)
go

--------------------insert into table here-----------

insert into Account(username, password, role) values
	('admin', '7c222fb2927d828af22f592134e8932480637c0d', 2),
	('sale', '7c222fb2927d828af22f592134e8932480637c0d', 1),
	('customer', '7c222fb2927d828af22f592134e8932480637c0d', 0),
	('meobeongungoc', '7c222fb2927d828af22f592134e8932480637c0d', 0)
go

insert into Employee(emID, accID, emName, gender, email, emPhone, emAddress, emImage) values
	('namdd', 1, 'admin', 1, 'namddhe150684@fpt.edu.vn', '0123456789', N'Thôn Thế Trụ, Xã Nghĩa Hương, Huyện Quốc Oai, Thành phố Hà Nội', 'img/user/employee/em1.jpg'),
	('baonn', 2, 'sale', 1, 'namddhe150684@fpt.edu.vn', '0123456789', N'Thôn 9, Xã Thạch Hoà, Huyện Thạch Thất, Thành phố Hà Nội', 'img/user/employee/em2.jpg')
go

insert into Customer(cusID, accID, cusName, gender, email, cusPhone, cusAddress, cusImage, updateBy) values
	('CUS1', 3, N'Vuong Loi Nha', 0, 'vuongloinha@gmail.com', '0123456789', N'Thôn Thế Trụ, Xã Nghĩa Hương, Huyện Quốc Oai, Thành phố Hà Nội', 'img/user/customer/cus1.jpg', ''),
	('CUS2', 4, N'Meo Beo', 0, 'meobeo@gmail.com', '0123456798', N'Thôn 9, Xã Thạch Hoà, Huyện Thạch Thất, Thành phố Hà Nội', 'img/user/customer/cus2.jpg', '')
go

insert into Category values
	('ME', N'Thịt'),
	('EG', N'Trứng'),
	('VE', N'Rau Củ'),
	('FR', N'Hoa Quả'),
	('SF', N'Thủy - Hải Sản')
go

insert into Product(proID, cateID, proName, price, type, quantity, image, description, rated) values
	('10140279', 'ME', N'Thịt nạc vai heo Meat Deli', 55960, N'Hộp', 20, 'img/product/meat/ThitNacVaiHeoMeatDeli.jpg', N'Thịt nạc vai là phần thịt nằm ở vị trí vai của con heo. Phần thịt này thường dày, đầy đặn, có độ dai và giòn và tỷ lệ nạc mỡ cân bằng nhau nên đây cũng là khúc thịt heo rất ngon được nhiều người lựa chọn. Thịt vai heo nói riêng và Thịt heo nói chung cung cấp đầy đủ các thành phần dinh dưỡng, cung cấp chất đạm, chất béo, các vitamin và muối khoáng cho cơ thể. Trong 100g thịt nạc vai chứa thành phần dinh dưỡng như sau: 19g protein, 7g mỡ, 7mg canxi, 190mg phosphor, 1.5mg sắt, 2.5mg kẽm, 341mg kali, 76mg natri, 2μg vitamin A.', 0),
	('10281592', 'ME', N'Sườn St. Louis MeatDeli Premium', 81363, N'Quả', 20, 'img/product/meat/SuonStLouisMeatDeliPremium.jpg', N'Sườn heo St.Louis là phần sườn nhiều thịt được cắt từ phần bụng. Phần thịt này phẳng hơn so với phần dẻ sườn thăn heo nên sẽ dễ dàng để chiên vàng hơn. Tỷ lệ xương và mỡ cũng nhiều hơn nên hương vị sẽ đậm đà hơn. Thịt heo chứa hàm lượng cao protein và các acid amin, rất cần thiết đối với những người quan tâm đến việc xây dựng hình thể. Thành phần bên trong thịt heo chứa nhiều vitamin B1, vitamin B2 rất cần thiết cho sự tăng trưởng, phục hồi cơ bắp và nhanh hồi phục những tổn thương trên da và giải độc cơ thể rất tốt.', 0),
	('10281594', 'ME', N'Bắp giò cuộn MeatDeli Premium', 59465, N'0.5 KG', 20, 'img/product/meat/BapGioCuonMeatDeliPremium.jpg', N'Bắp giò heo là phần thịt ở trên bắp đùi heo, chứa giá trị dinh dưỡng cao với tỷ lệ nạc nhiều, có nhiều bắp thịt cuộn lại săn chắc, ngọt thịt, thường có gân, khi ăn giòn sần sật. Bắp giò heo rút xương dùng để chế biến các món luộc, hầm hạt sen và nấm đông cô, bắp giò muối, bắp giò kho, bắp giò chiên nước mắm... đều rất ngon.', 0),
	('10281595', 'ME', N'Móng giò Meat Deli Premium', 43960, N'0.2 KG', 20, 'img/product/meat/MongGioMeatDeliPremium.jpg', N'Móng giò có nhiều gân, thịt mỡ phần này khi ăn cũng không bị cảm giác gây béo, da giòn. Móng giò rất phổ biến là một món ăn dùng để tăng cân cho những người gầy, hoặc là món ăn giúp lợi sữa cho các mẹ sau khi sinh. Có nhiều món ăn được chế biến từ chân giò, chủ yếu là món ninh nhừ như canh hầm, kho, nấu đông, giả cầy,...', 0),
	('10195050', 'EG', N'Trứng gà bói hộp 12 quả Dabaco', 41000, N'0.5 KG', 20, 'img/product/egg/TrungGaBoiHop12QuaDabaco.jpg', N'Trứng gà sạch đóng hộp 12 quả được đóng gói và bảo quản theo những tiêu chuẩn nghiêm ngặt về vệ sinh và an toàn thực phẩm, đảm bảo về chất lượng của thực phẩm. Trứng gà to tròn, đều. Trứng gà thì bạn có thể luộc chín chế biến thành một số món ăn khác như: thịt kho trứng, cơm chiên trứng,...', 0),
	('10140622', 'SF', N'Tôm sú tươi 25-30 con/kg', 153000, N'Gói 0.5 KG', 20, 'img/product/seafood/TomSuTuoi2530ConKg.jpg', N'Tôm sú là loại tôm đạt chuẩn xuất khẩu, tươi ngon, có thịt ngọt và dai, là loại hải sản giàu dinh dưỡng, cung cấp hàm lượng canxi và sắt rất cao cho chế độ ăn hằng ngày. Tôm sú còn mang lại nhiều lợi ích cho sức khỏe.', 0),
	('10310866', 'SF', N'Tôm thẻ 70-80 con/kg', 89700, N'0.3 KG', 20, 'img/product/seafood/TomThe7080ConKg.jpg', N'Tôm thẻ là loại tôm ngon, giàu chất dinh dưỡng, giá thành rẻ rất được nhiều người nội trợ ở Việt Nam tin dùng, ưa chuộng. Tại Việt Nam, tôm thường được nuôi trồng tại các vùng cửa sông, cửa biển, nơi vùng nước lợ. Tôm thẻ 70-80 con/kg có vỏ mỏng, màu trắng đục, chân màu trắng, dài tầm 15-20 cm. Tôm thẻ có 1 đốt đầu, 6 đốt bụng và 1 đốt đuôi. Đốt thường mang trứng, rãnh bụng rất hẹp hoặc gần như không có.', 0),
	('10053469', 'SF', N'Nghêu sạch Lenger khay 600g', 38000, N'1 KG', 20, 'img/product/seafood/NgheuSachLengerKhay600g.jpg', N'Ngao (nghêu) là loại thực phẩm phổ biến, rẻ, có hàm lượng chất dinh dưỡng cao và tốt cho sức khỏe. Trong thịt ngao có nhiều chất protit, gluxit, lipid, nhiều vitamin và những khoáng chất cần thiết cho cơ thể, rất tốt cho người mới ốm dậy. Trọng thịt ngao còn có nhiều phôtpho, chất cần cho sự hình thành xương, răng của bào thai, rất bổ dưỡng cho phụ nữ có thai. Hàm lượng protein trong ngao cao hơn nhiều so với thịt, giúp giữ dáng, đẹp da, thích hợp cho người ăn kiêng.', 0),
	('10054875', 'FR', N'Chuối dole', 27650, N'Quả', 20, 'img/product/fruit/ChuoiDole.jpg', N'Chuối Dole được trồng theo phương pháp hữu cơ, đảm bảo vệ sinh an toàn thực phẩm cho người sử dụng, đồng thời thời gian vận chuyển ngắn nên luôn giữ được độ tươi ngon khi đến tay người tiêu dùng. Chuối Dole là một giống chuối ngoại nhập, có mùi thơm và có vị ngọt đặc trưng. Đây là loại quả cung cấp kali và axit folic tuyệt vời cho sức khỏe. Bên cạnh đó, chuối còn giàu vitamin và khoáng chất tốt cho não bộ, hỗ trợ trí nhớ, bổ sung dưỡng chất, hỗ trợ hệ tiêu hóa và có tác dụng trong việc làm đẹp hiệu quả.', 0),
	('10054782', 'FR', N'Mận hậu', 47900, N'0.5 KG', 20, 'img/product/fruit/ManHau.jpg', N'Một loại đặc sản nổi tiếng của miền Bắc còn được gọi với tên mận Bắc hay mận Hà Nội. Mận hậu hộp 500g không chỉ có vị chua ngọt đầy kích thích vị giác mà còn mang đến nhiều công dụng cho sức khoẻ như kiểm soát lượng đường, làm chậm quá trình lão hóa, giảm táo bón, ngăn ngừa các bệnh tim mạch,...', 0),
	('10056225', 'FR', N'Lê đỏ Nam phi', 46950, N'1 KG', 20, 'img/product/fruit/LeDoNamPhi.jpg', N'Lê đỏ Nam phi có 3 màu đặc trưng là xanh - đỏ - vàng xen kẽ nhau rất đẹp, vỏ mỏng, ruột trắng, nhiều nước, rất giòn, ngọt dịu thơm. Loại lê này có xuất xứ từ các tỉnh phía Tây Nam của Nam Phi - nơi có khí hậu rất đa dạng, mát mẻ và khô vào mùa hè, là vùng đất tuyệt vời để trồng hoa quả. Tuy nhiên loại lê này là một loại đặc sản chỉ có sẵn trong một thời gian ngắn mỗi năm, chính vì vậy, để thưởng thức, người dùng cần nhanh tay mua khi vào mùa.', 0),
	('10054020', 'VE', N'Bắp cải trắng', 10140, N'1 KG', 20, 'img/product/vegetable/BapCaiTrang.jpg', N'Loại rau này có vị ngọt thanh đặc trưng, chứa nhiều chất chống oxy hóa, đặc biệt nhất là Sulforaphane – một chất có khả năng phá hủy được những tế bào gây nên bệnh ung thư. Ngoài ra, trong Bắp cải trắng còn có vitamin A, C và P. Nghiên cứu khoa học còn chứng minh được rằng phụ nữ ăn khoảng 4 – 5 bữa ăn có cải bắp trong tuần thì nguy cơ mắc bệnh ung thư vú sẽ giảm đến 74%. Bắp cải trắng thì chúng ta có thể chế biến được rất nhiều món ăn siêu ngon như súp, xào, luộc…', 0),
	('10053922', 'VE', N'Súp lơ (Bông cải) xanh', 38750, N'1 KG', 20, 'img/product/vegetable/SupLoBongCaiXanh.jpg', N'Hàm lượng chất chống oxy hóa của bông cải xanh có thể là thành phần chính tạo nên lợi ích của nó đối với sức khỏe con người. Hàm lượng chất chống oxy sẽ gây ức chế hoặc vô hiệu hóa tổn thương tế bào do các gốc tự do gây ra. Bông cải xanh có thể giúp cho người mắc bệnh tiểu đường kiểm soát lượng đường trong máu tốt hơn. Bông cải xanh rất giàu chất xơ và chất chống oxy hóa, giúp ruột khỏe mạnh và tăng cường chức năng của hệ tiêu hóa.', 0),
	('10054769', 'VE', N'Cà chua cherry đỏ WinEco', 23940, N'0.5 KG', 20, 'img/product/vegetable/CaChuaCherryDoWinEco.jpg', N'Công dụng: Cải thiện thị lực; Ngăn ngừa ung thư; Giúp da đẹp Giảm lượng đường trong máu; Giúp giảm cân; Giúp xương chắc khỏe Chất chống oxy hóa có trong cà chua là thành phần chủ yếu có trong các sản phẩm sữa rửa mặt. Các chất oxy hóa này giúp tẩy tế bào chết và phục hồi các tế bào bề mặt, từ đó chúng làm sáng da và mang lại cho bạn khuôn mặt rạng rỡ. Đắp vài lát cà chua lên da trong vòng 10 phút là bạn sẽ thấy ngay tác dụng của nó đối với làn da. Bên cạnh đó, nước ép cà chua là phương thuốc tự nhiên giúp trị mụn trứng cá và làm se khít lỗ chân lông.', 0),
	('10053905', 'VE', N'Mướp hương', 6450, N'1 KG', 20, 'img/product/vegetable/MuopHuong.jpg', N'Mướp hương là một trong những loại rau củ rất quen thuộc đối với người Việt Nam. Với mướp hương, chúng ta có thể chế biến được rất nhiều món ăn hấp dẫn và giàu dinh dưỡng. Mỗi quả mướp hương được nuôi trồng và chăm chút rất cẩn thận. Những sản phẩm được bày bán trên gian hàng đều đã trải qua quá trình tuyển chọn kỹ càng. Sản phẩm được phân phối bởi Vinmart và được bảo quản cẩn thận và chặt chẽ để mang tới cho khách hàng những sản phẩm có chất lượng tốt nhất.', 0),
	('10055070', 'FR', N'Táo Queen New Zealand', 99900, N'Hộp', 20, 'img/product/fruit/TaoQueenNewZeeland.jpg', N'Táo Queen New Zealand PG size 90-120 được lai tạo giữa táo Gala và táo Splendour, là loại táo nổi tiếng về độ ngọt, rất giòn nhưng không quá cứng, màu sắc đỏ thẫm. Táo Queen PG có quả tròn, vỏ mỏng có màu sắc đỏ thẫm, trọng lượng khoảng 7 đến 8 quả/kg. Không chỉ vậy, táo còn rất thơm, nhiều nước và được xem là một trong những loại Táo New Zealand phù hợp nhất với khẩu vị của người Việt Nam.', 0),
	('10054870', 'FR', N'Bưởi hồng da xanh túi lưới', 70680, N'Hộp', 20, 'img/product/fruit/BuoiHongDaXanhTuiLuoi.jpg', N'Bưởi hồng da xanh túi lưới 1.4kg là một loại quả đặc sản của miền Tây Nam Bộ. Với các múi khi chín màu hồng đỏ rất dễ tách, múi bưởi mọng nước, vị ngọt, mùi thơm khi thưởng thức. Trở thành loại quả được sử dụng phổ biến và được nhiều người ưa chuộng! Trái bưởi hồng da xanh có dạng hình cầu, nặng trung bình từ 1,2 -2.5 kg/quả. Khi chín, vỏ trái có màu xanh đến xanh hơi vàng, dễ bóc và khá mỏng tầm 14-18mm. Sản phẩm được bọc lưới đẹp mắt chắc chắn lịch sự rất thích hợp để làm món quà biếu tặng dịp lễ tết hay dâng cúng, trưng bày rất sang trọng cũng như là trái cây bổ dưỡng tẩm bổ cho người ốm, người già hay trẻ con.', 0),
	('10140236', 'FR', N'Cam Ai Cập', 32000, N'0.5 KG', 20, 'img/product/fruit/CamAiCap.jpg', N'Cam vàng Ai Cập có màu sắc vàng tươi, quả rất to và mọng nước, đặc biệt Cam vàng Ai Cập còn có mùi thơm rất đặc trưng. Cam vàng Ai Cập có hàm lượng Vitamin C cao, rất tốt cho da, góp phần chống lão hóa và giảm Cholesterol, có tác dụng hồi phục sức khỏe nhanh, tăng cường chức năng tạo hồng huyết cầu và giảm căng thẳng thần kinh. Việc tiêu thụ vitamin C ở liều cao (khoảng 500mg mỗi ngày) rất tốt cho người ốm. Quả cam mọng nước chứa một hàm lượng Vitamin C cao đến mức chỉ cần ăn một quả cũng đáp ứng được 130% nhu cầu vitaminC hàng ngày của chúng ta. Ngoài ra, cam còn được biết tới như một loại đồ ăn kiêng giàu chất xơ, Vitamin A, B, Canxi, Magnesium, Sắt, Đồng, Iốt…', 0),
	('10054693', 'VE', N'Rau muống WinEco', 12000, N'0.5 KG', 20, 'img/product/vegetable/RauMuongWinEco.jpg', N'Rau muống Loại 1 WinEco là một trong những loại thực phẩm quen thuộc hàng ngày, có giá rất rẻ, dễ ăn và hầu như ai cũng biết đến. Loại rau này chứa nhiều kali, canxi, đồng, và các khoáng chất khác như sắt, magie, phospho, natri. Ngoài ra các loại Vitamin như C, E, K, B1, niacin, B5, B6, folate và biotin. Rau muống chứa một lượng lớn vitamin A, C, các chất dinh dưỡng và đặc biệt là hàm lượng chất sắt dồi dào. Đặc biệt rau muống giúp: Hỗ trợ điều trị thiếu máu, phòng chống tiểu đường, bảo vệ sức khỏe tim mạch, điều trị bệnh vàng da và các vấn đề về gan,…', 0),
	('10053944', 'VE', N'Hành tây', 6000, N'0.5 KG', 20, 'img/product/vegetable/HanhTay.jpg', N'Hành tây có một số lợi ích sức khỏe, chủ yếu là do hàm lượng chất chống oxy hóa cao và các hợp chất chứa lưu huỳnh. Ăn hành tây có tác dụng kháng viêm, đồng thời giảm nguy cơ ung thư, hạ lượng đường trong máu và cải thiện sức khỏe của xương. Hành sống rất ít calo, chỉ khoảng 40 calo trên mỗi 3,5 ounce (100 gram). Một củ hành tươi có 89% là nước, 9% carbs và 1,7% chất xơ, kèm theo một lượng nhỏ protein và chất béo.', 0),
	('10617963', 'ME', N'Thịt heo xay đặc biệt Meat Deli', 52500, N'0.5 KG', 20, 'img/product/meat/ThitHeoXayDacBietMeatDeli.jpg', N'Meat Deli thương hiệu thịt sạch áp dụng Công Nghệ Oxy Fresh 9 đến từ Châu Âu mang tới những sản phẩm đảm bảo an toàn chất lượng tới tận tay người tiêu dùng. Khép kín mọi công đoạn giúp nâng cao dinh dưỡng trong bữa ăn của mỗi gia đình. Thịt lợn chứa hàm lượng cao protein và các acid amin, rất cần thiết đối với những người quan tâm đến việc xây dựng hình thể. Thành phần bên trong thịt lợn chứa nhiều vitamin B1, vitamin B2 rất cần thiết cho sự tăng trưởng, phục hồi cơ bắp và nhanh hồi phục những tổn thương trên da và giải độc cơ thể rất tốt.', 0),
	('10195014', 'EG', N'Trứng gà sạch OLALA', 30000, N'0.5 KG', 20, 'img/product/egg/TrungGaSachOlala.jpg', N'Trứng gà sạch đóng hộp 10 quả. Xuất xứ từ Việt Nam. Bảo quản trong tủ lạnh để giữ được độ tươi ngon của sản phẩm. Sử dụng khi còn tươi.', 0),
	('10282045', 'SF', N'Đầu mực lá gói 500g', 123000, N'0.5 KG', 20, 'img/product/seafood/DauMucLaGoi500g.jpg', N'Đầu mực tươi được đánh bắt trực tiếp từ biển. Bảo quản ở nhiệt độ thấp để giữ được độ tươi ngon của sản phẩm.', 0)
go

insert into [Order](orderID, cusID, cusName, email, cusPhone, cusAddress, total, status) values
	('OID1', 'CUS1', N'Vuong Loi Nha', 'vuongloinha@gmail.com', '0123456789', N'Yuan Lin, Chang Hua, Taiwan', 591100, 0),
	('OID2', 'CUS2', N'Meo Beo', 'meobeo@gmail.com', '0123456798', N'Quoc Oai, Ha Noi, Viet Nam', 571200, 0)

go
insert into OrderDetail values
	('10282045', 'OID2', 4, 123000, 492000),
	('10055070', 'OID2', 1, 99900, 99900),
	('10055070', 'OID1', 2, 99900, 199800),
	('10054870', 'OID1', 5, 70680, 353400),
	('10053944', 'OID1', 3, 6000, 18000)

go
insert into Cart(cusID) values
	('CUS1')

go 
insert into CartDetail values
	(1, '10055070', 2),
	(1, '10054693', 1),
	(1, '10282045', 3)

go

insert into Post(thumbnail, title, description, author, flag, status) values
	('img/post/post01.jpg', N'Bí quyết vắt và bảo quản để nước cam không bị đắng', N'Chúng ta thường thấy nước cam sau khi vắt để một thời gian ngoài không khí sẽ có vị đắng rất khó uống. Bài viết này sẽ cho bạn biết nguyên nhân và cách giải quyết tình trạng này.
Nhắc đến cam thì chắc chắn phải nói đến vitamin C. Dinh dưỡng đến từ vitamin C giúp ngăn ngừa xơ cứng động mạch, làm giảm cholesterol ở gan. Đặc biệt, vitamin C trong trái cam sành còn là một chất chống oxy hoá rất tốt, giúp bảo vệ các tế bào trên cơ thể, khiến làn da mịn màng, trẻ trung hơn và còn nhiều công dụng từ trái cam mà bạn có thể chưa biết tới. Nước cam là thức uống vừa ngon vừa bổ dưỡng cho mọi người. Tuy nhiên, hẳn ai trong chúng ta cũng đã từng phải "nếm trải" ly nước cam có vị đắng khó chịu. Vì sao lại như thế và phải làm sao để tránh tình trạng này?
Nước cam bị đắng không phải do không khí. Tinh dầu trong vỏ cam chính là nguyên nhân làm cho nước cam bị đắng. Tinh dầu trong vỏ cam có tính ấm, tác dụng trị ho, giảm đau và hỗ trợ tiêu hóa. Tuy nhiên nó lại có vị cay, đắng rất khó uống.
Vì không khí không phải là nguyên nhân chính làm cam bị đắng, do đó cách tối ưu nhất để nước cam có thể giữ lâu không bị đắng chính là hạn chế tối đa tinh dầu ở vỏ bị nhiễm trong nước cam. Vì vậy, ngay từ khâu vắt cam, bạn phải chú ý thực hiện một số cách sau để tránh tinh dầu lẫn vào nước:
- Gọt bỏ vỏ cam trước khi vắt. Lớp vỏ ngoài được lấy đi sẽ loại bỏ khả năng tiết dầu tối đa, lúc này bạn chỉ cần vắt nhẹ và nước cam sẽ không bị nhiễm dầu đắng khó uống nữa. Từ đó việc để lâu thì nước cam cũng sẽ không bị đắng.
- Quay quả cam trong lò vi sóng trong 30 giây, việc này làm cho tinh dầu ở vỏ cam bốc hơi và không bị lẫn vào nước cam khi bạn vắt nữa.
- Dùng dụng cụ hoặc máy vắt nước cam sẽ hạn chế được nước cam bị đắng sau khi vắt.
Khi vắt được nước cam không bị lẫn tinh dầu, bạn có thể an tâm rằng nước cam của bạn đã hạn chế được vị đắng. Nhưng khâu bảo quản cũng vô cùng quan trọng để giữ được vị ngon và dinh dưỡng có trong nước cam, đồng thời hạn chế những phản ứng tạo ra vị đắng của nó.', N'Bách Hóa Xanh', 0, 1),
	('img/post/post02.jpg', N'Làm nước cam cà rốt thanh nhiệt, giảm cân, bổ dưỡng', N'Cam và cà rốt đều là thực phẩm quen thuộc với chúng ta. Mỗi loại tuy có phong vị, công dụng riêng nhưng khi được kết hợp cùng nhau đã tạo thành một tuyệt phẩm vừa ngon, vừa tốt cho sức khỏe. Hãy làm ngay với cách pha cam cà rốt dưới đây nhé.
Với nguồn vitamin và khoáng chất dồi dào có sẵn trong cam và cà rốt, món nước ép này sẽ giúp bạn giải nhiệt cơ thể tốt nhất đó.
Trong cà rốt có chứa hàm lượng Betacarotene cao, khi đi vào cơ thể sẽ chuyển hóa thành vitamin A, giúp làm chậm quá trình lão hóa, thúc đẩy quá trình sản xuất collagen và trị mụn trứng cá vô cùng hiệu quả.
Còn lượng vitamin C trong cam sẽ làm săn chắc cơ bắp an toàn và hiệu quả. Đặc biệt, khi kết hợp cùng nhau, chất chống oxy hóa có trong cả hai giúp ngăn ngừa sự hình thành và phát triển của tế bào ung thư nữa đấy.
Bước 1: Sơ chế nguyên liệu
Đầu tiên, rửa sạch các nguyên liệu với nước. Cà rốt đem gọt vỏ, rồi cắt thành những đoạn nhỏ. Còn cam thì cắt làm đôi.
Bước 2: Vắt cam
Chuẩn bị dụng cụ vắt cam, sau đó vắt lần lượt từng miếng vào.
Bước 3: Hoàn thành nước cam cà rốt
Cho cà rốt cùng nước lọc vào máy ép, ép đến khi ra hết nước, còn xác cà rốt thì ta bỏ nhé. Nếu không có máy ép thì có thể dùng máy xay sinh tố, sau khi xay xong, ta lọc hỗn hợp qua rây hoặc vải mùng là được. Cuối cùng, ta trộn nước ép cà rốt cùng nước cam lại với nhau là đã hoàn thành rồi đấy.', N'Bách Hóa Xanh', 0, 1),
	('img/post/post03.jpg', N'Uống nước cam mỗi ngày có tốt không?', N'Nước cam rất tốt cho sức khỏe, vì thế mọi người nghĩ rằng có thể uống thoải mái và uống mỗi ngày đều được. Vậy uống nước cam mỗi ngày có tốt không?
Hàm lượng Vitamin C có trong quả cam rất cao, là dưỡng chất quan trọng trong việc chống oxy hóa, giúp da căng mọng, mịn màng hơn. Ngoài ra, Vitamin C còn hỗ trợ ngăn ngừa chứng xơ cứng động mạch, giảm Cholesterol ở gan và duy trì hàm lượng Cholesterol ở mức ổn định.- Bên cạnh đó, quả cam còn chứa nhiều khoáng chất quan trọng như Kali, Thiamin, Carotenoid… với chức năng chính là thanh lọc độc tố, tăng sức đề kháng cho cơ thể.
Mặc dù nước cam rất có lợi cho sức khỏe nhưng nếu uống mỗi ngày sẽ không thực sự tốt như bạn nghĩ! Bởi vì, một khi bạn uống chúng quá thường xuyên, các axit có trong nước cam sẽ liên tục bào mòn men răng, lâu dần sẽ gây ê buốt răng. Tuy lượng đường trong cam cực thấp nhưng vẫn thực sự nguy hiểm với những người bệnh đái tháo đường và khiến tình trạng bệnh nặng hơn. Ngoài ra, nhiều người có thói quen cho đường vào khi uống vì cam có vị chua, tuy nhiên như vậy sẽ tăng nguy cơ bị viêm khớp, đau khớp hơn.
Không nên uống nước cam vào ban đêm đặc biệt trước khi ngủ, như vậy sẽ làm ảnh hưởng giấc ngủ bởi nước cam có tính lợi tiểu. Cũng không nên uống sau khi ăn no sẽ gây áp lực cho dạ dày, gây tức bụng. Thời gian lý tưởng nhất để uống nước cam là sau khi ăn khoảng 1 đến 2 giờ.
Tóm lại là việc uống nước cam sẽ có cả mặt lợi và mặt hại nếu không điều độ. Tuy nhiên theo giới truyền thông thì trong mọi tình huống, dù đó có là vấn đề to nhỏ thế nào đi chăng nữa thì bạn nên uống nước cam. Lý do rất đơn giản, tuy không giải quyết được vấn đề nhưng mà nó ngon.', N'Bách Hóa Xanh', 0, 1),
	('img/post/post04.jpg', N'Nên uống nước cam hay sữa vào buổi sáng?', N'Nước cam và sữa đều là những thức uống quen thuộc và bổ dưỡng của nhiều gia đình sau mỗi bữa ăn. Tuy nhiên, nên uống nước cam hay sữa vào buổi sáng sẽ tốt hơn? So sánh ưu nhược điểm để chọn lựa nhé!
Một ly nước cam 250ml cung cấp 110 calo, cho nguồn năng lượng tuyệt vời để khởi đầu ngày mới thật hiệu quả. Uống 1 ly nước cam cung cấp đủ lượng Vitamin C cần thiết cho cơ thể trong 1 ngày. Vitamin C là chất chống oxy hóa mạnh, có thể bảo vệ da khỏi tác động của ánh nắng mặt trời và não bộ khỏi các chất ô nhiễm như than chì. Uống nước cam vào buổi sáng khi dạ dày còn trống, tốt nhất là 10 phút trước khi ăn sáng, là thời điểm tốt nhất giúp dạ dày hấp thụ các chất dinh dưỡng.
Tuy nhiên nếu bạn có thói quen đánh răng sau bữa ăn sáng, hãy cẩn thận khi chọn uống nước cam. Trong nước cam chứa lượng axit lớn, chúng sẽ bám vào bề mặt của men răng, và dưới tác dụng chà xát của bàn chải có thể làm cho men răng của bạn bị tổn thương. Bạn nên súc miệng sau khi uống nước cam để loại trừ sự bám dính axit này trên răng.
Một cốc sữa ít béo chứa khoảng 20% lượng Protein cần thiết 1 ngày và 1/3 lượng Canxi. Sữa chứa 9 dưỡng chất thiết yếu: Niacin, Protein, Vitamin A, Riboflavin, Vitamin B12, Phosphorus, Calcium, Potassium, Vitamin D; trong đó có những dưỡng chất thường thiếu hụt trong chế độ ăn hàng ngày của người Việt. Người uống sữa vào buổi sáng thường không có xu hướng ăn quá nhiều vào bữa trưa, do cảm giác thỏa mãn tự nhiên về Protein và Calcium kích thích hoạt động của Hormone kiểm soát cân nặng.
Tuy nhiên sữa có chứa chất béo bão hòa, có thể làm tăng nguy cơ mắc bệnh tim mạch và tiểu đường nếu lạm dụng. Phần lớn sữa động vật nhiễm Hormone tăng trưởng và kháng sinh (do thuốc tiêm, thức ăn, nước uống trong quá trình chăn nuôi động vật lấy sữa), chúng cũng là những nguyên nhân dẫn đến nhiều bệnh tật và hệ lụy lên sức khỏe con người.
Các chuyên gia khuyến cáo nên chọn sữa cho bữa sáng, vì nó cung cấp nhiều dưỡng chất cần thiết hơn, cung cấp Canxi, giúp tái tạo men răng,... Nếu bạn vẫn muốn uống nước cam cho bữa sáng, một lời khuyên nhỏ là nên uống nhanh, không nên uống nhâm nhi từng ngụm và nên súc miệng sau khi uống để tránh làm hại men răng.', N'Bách Hóa Xanh', 0, 1),
	('img/post/post05.jpg', N'Uống nước cam chanh khi đau dạ dày rất tốt nếu áp dụng cách này', N'Nhiều người truyền tai rằng không được uống nước cam, chanh khi đau dạ dày, vậy việc này có thật sự đúng? Cùng tìm hiểu qua bài viết này nhé!
Nước cam chanh ngoài là món nước được ưa chuộng để giải nhiệt, nó còn đem lại rất nhiều lợi ích cho sức khỏe. Tuy nhiên, có rất nhiều người truyền tai rằng uống cam chanh sẽ gây hại khi đau dạ dày? Cùng Bách hóa XANH tìm hiểu thực hư việc này và hướng dẫn bạn cách uống nước cam chanh khi bị đau dạ dày nhé!
Theo các nhà nghiên cứu, cam và chanh chứa rất nhiều axit hữu ích cho hệ tiêu hóa, giúp thúc đẩy dịch dạ dày hoạt động ổn định và giảm các triệu chứng tiêu hóa như: Ợ chua, đầy hơi và khó tiêu. Bên cạnh đó, nồng độ vitamin C trong cam chanh giúp làm giảm tỷ lệ mắc bệnh dạ dày và bảo vệ chống lại vi khuẩn HP. Tuy nhiên, nếu lượng axit này được tiêu thụ với nồng độ cao quá thường xuyên, chúng có thể làm tăng tiết dịch vị, kích thích niêm mạc dạ dày và gây ra các kích ứng khó chịu, đặc biệt là ở người bệnh dạ dày. Người bị đau dạ dày vẫn có thể uống nước cam chanh nhưng phải được uống đúng cách, phù hợp liều lượng và vào thời điểm thích hợp. Nếu có thể đảm bảo uống cam chanh hợp lý còn có thể cải thiện bệnh đau dạ dày.
Cam và chanh là loại trái cây mà hầu hết chúng ta đều quen thuộc. Không chỉ được chế biến thành một loại nước giải nhiệt mà còn mang lại nhiều lợi ích sức khỏe khác. Cùng điểm qua các tác dụng có ích của loại trái cây này nhé.
Vitamin C là loại vitamin hỗ trợ tăng cường hệ miễn dịch. Nước cam chanh được nhắc đến như loại quả đại diện cho loại vitamin này, do đó uống nước cam chanh sẽ giúp tăng cường miễn dịch và chống lại cảm lạnh, cảm cúm hiệu quả hơn.
Hầu như tất cả mọi người đều biết cam và chanh có tác dụng hạ sốt, đặc biệt là gia đình có con nhỏ. Bạn có thể nhúng khăn vào hỗn hợp dung dịch nước ấm và cam chanh, sau đó vắt khô rồi đắp lên trán trẻ. Bạn sẽ thấy hạ sốt nhanh chóng chỉ trong thời gian ngắn.
Theo nhiều nghiên cứu, cam và chanh được xác định là một trong những loại thực phẩm có thể giúp ngăn ngừa lão hóa da. Nước chanh có chứa chất chống oxy hóa giúp giữ gìn làn da và ngăn ngừa nếp nhăn bằng cách chuyển đổi các axit amin thành collagen.', N'Bách Hóa Xanh', 0, 1)

go
insert into PostList values
	(1, '10140236'),
	(2, '10140236'),
	(3, '10140236'),
	(4, '10140236'),
	(5, '10140236')

go
insert into Slider(slideID, slideImage, title, description, backlink, status, notes) values
	('SLI1', 'img/slider/slide01.jpg', N'Táo tươi ngon cho mùa hè', N'Táo của chúng tôi có nhiều công dung như: để ăn, để làm salad, để làm sinh tố, để ngâm rượu hay dùng để đi thăm đứa bạn đang nằm viện do bị đánh vì trễ deadline.', '/FreshFoodMarket/productDetail?proid=10055070', 1, null),
	('SLI2', 'img/slider/slide02.jpg', N'Cam thần thánh', N'Khi bạn stress vì bị deadline dí, hãy uống nước cam. Tuy nó không giúp bạn hết stress nhưng mà nó ngon.', '/FreshFoodMarket/productDetail?proid=10140236', 1, null),
	('SLI3', 'img/slider/slide03.jpg', N'Thực phẩm cho sinh viên cuối tháng', N'Sinh viên vào cuối tháng là khi ví chỉ còn vài nghìn. Nhưng đừng lo, với ngân sách ít ỏi chúng tôi có trứng để giúp bát mỳ tôm của bạn nhiều dinh dưỡng hơn.', '/FreshFoodMarket/categories?idC=EG', 1, null),
	('SLI4', 'img/slider/slide04.jpg', N'Thực phẩm cho sinh viên đầu tháng', N'Ngoài những sản phẩm cho sinh viên vào cuối tháng thì chúng tôi cũng có đủ những sản phẩm cho sinh viên mới xin được tiền của phụ huynh.', '/FreshFoodMarket/categories?idC=ME', 1, null)

go

----------------trigger here-------------------------

create trigger update_UpdateDate_Product
on Product
for update
as
begin
	Update Product
	set updateDate = getDate()
	from Product
	where proID like (select proID from inserted)
end
go

create trigger update_UpdateDate_Customer
on Customer
for update
as
begin
	Update Customer
	set updateDate = getDate()
	from Customer
	where cusID like (select cusID from inserted)
end
go