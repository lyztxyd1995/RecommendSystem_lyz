����   4h  db/mysql/MySQLConnection  java/lang/Object  db/DBConnection conn Lcom/mysql/jdbc/Connection; <init> ()V Code
   	 
  com.mysql.jdbc.Driver
    java/lang/Class   forName %(Ljava/lang/String;)Ljava/lang/Class;
     newInstance ()Ljava/lang/Object;  Zjdbc:mysql://localhost:3306/lyz_RecommendSystem?user=root&password=root&autoreconnect=true
    java/sql/DriverManager   ! getConnection )(Ljava/lang/String;)Ljava/sql/Connection; # com/mysql/jdbc/Connection	  %  
 ' ) ( java/lang/Exception * 
 printStackTrace LineNumberTable LocalVariableTable this Ldb/mysql/MySQLConnection; e Ljava/lang/Exception; StackMapTable close " 4 2 
	 6 8 7 java/lang/System 9 : out Ljava/io/PrintStream;
 ' < = > 
getMessage ()Ljava/lang/String;
 @ B A java/io/PrintStream C D println (Ljava/lang/String;)V setFavoriteItems %(Ljava/lang/String;Ljava/util/List;)V 	Signature 9(Ljava/lang/String;Ljava/util/List<Ljava/lang/String;>;)V J 4INSERT INTO history (user_id, item_id) VALUES (?, ?) " L M N prepareStatement 0(Ljava/lang/String;)Ljava/sql/PreparedStatement; P R Q java/util/List S T iterator ()Ljava/util/Iterator; V X W java/util/Iterator Y  next [ java/lang/String ] _ ^ java/sql/PreparedStatement ` a 	setString (ILjava/lang/String;)V ] c d e execute ()Z V g h e hasNext
 j ) k java/sql/SQLException userId Ljava/lang/String; itemIds Ljava/util/List; query 	statement Ljava/sql/PreparedStatement; itemId Ljava/sql/SQLException; LocalVariableTypeTable $Ljava/util/List<Ljava/lang/String;>; unsetFavoriteItems y 5DELETE FROM history WHERE user_id = ? and item_id = ? getFavoriteItemIds #(Ljava/lang/String;)Ljava/util/Set; 7(Ljava/lang/String;)Ljava/util/Set<Ljava/lang/String;>; ~ java/util/HashSet
 }  � -SELECT item_id from history WHERE user_id = ? ] � � � executeQuery ()Ljava/sql/ResultSet; � item_id � � � java/sql/ResultSet � � 	getString &(Ljava/lang/String;)Ljava/lang/String; � � � java/util/Set � � add (Ljava/lang/Object;)Z � � Y e favoriteItems Ljava/util/Set; sql rs Ljava/sql/ResultSet; #Ljava/util/Set<Ljava/lang/String;>; getFavoriteItems 2(Ljava/lang/String;)Ljava/util/Set<Lentity/Item;>;
  � z { � R � &SELECT * from items WHERE item_id = ?  � entity/Item$ItemBuilder
 � 
 � � � � 	setItemId -(Ljava/lang/String;)Lentity/Item$ItemBuilder; � name
 � � � � setName � city
 � � � � setCity � state
 � � � � setState � country
 � � � � 
setCountry � zipcode
 � � � � 
setZipcode � rating � � � � 	getDouble (Ljava/lang/String;)D
 � � � � 	setRating (D)Lentity/Item$ItemBuilder; � address
 � � � � 
setAddress � latitude
 � � � � setLatitude � 	longitude
 � � � � setLongitude � description
 � � � � setDescription � snippet
 � � � � 
setSnippet � snippet_url
 � � � � setSnippetUrl � 	image_url
 � � � � setImageUrl � url
 � � � � setUrl � *SELECT * from categories WHERE item_id = ? � category
 � � � � setCategories *(Ljava/util/Set;)Lentity/Item$ItemBuilder;
 � � �  build ()Lentity/Item; builder Lentity/Item$ItemBuilder; 
categories Ljava/util/Set<Lentity/Item;>; getCategories 2SELECT category from categories WHERE item_id = ?  searchItems 8(Ljava/lang/String;DDLjava/lang/String;)Ljava/util/List; G(Ljava/lang/String;DDLjava/lang/String;)Ljava/util/List<Lentity/Item;>;
 external/ExternalAPIFactory getExternalAPI ()Lexternal/ExternalAPI; external/ExternalAPI search &(DDLjava/lang/String;)Ljava/util/List; entity/Item
  saveItem (Lentity/Item;)V lat D lon term api Lexternal/ExternalAPI; items item Lentity/Item; Ljava/util/List<Lentity/Item;>;( ?INSERT IGNORE INTO items VALUES (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)
*+ > 	getItemId
-. > getName
01 > getCity
34 > getState
67 > 
getCountry
9: > 
getZipcode
<=> 	getRating ()D ]@AB 	setDouble (ID)V
DE > 
getAddress
GH> getLatitude
JK> getLongitude
MN > getDescription
PQ > 
getSnippet
ST > getSnippetUrl
VW > getImageUrl
YZ > getUrl\ *INSERT IGNORE INTO categories VALUES (?,?)
^_ ()Ljava/util/Set; getFullname verifyLogin '(Ljava/lang/String;Ljava/lang/String;)Z password 
SourceFile MySQLConnection.java InnerClasses ItemBuilder !             	 
     �     "*� � � W*� � "� $� L+� &�     '  +                ! ! ,       " - .     / 0  1    �     '  2 
     x     *� $� *� $� 3 � L� 5+� ;� ?�     '  +       '  )  *  +  0 ,        - .    
 / 0  1    S '
  E F  G    H   E     ^*� $� �IN*� $-� K :,� O :� *� U � Z:+� \ � \ � b W� f ��ҧ 
:� i�   S V j  +   2    5  6  8  :  ; . < 7 = A > I ; S @ X A ] E ,   H    ^ - .     ^ l m    ^ n o   S p m   < q r  .  s m  X  / t  u       ^ n v  1   5 �    Z P Z ]  V  &�    Z P Z  j  w F  G    H   E     ^*� $� �xN*� $-� K :,� O :� *� U � Z:+� \ � \ � b W� f ��ҧ 
:� i�   S V j  +   2    J  K  M  O  P . Q 7 R A S I P S U X V ] Y ,   H    ^ - .     ^ l m    ^ n o   S p m   < q r  .  s m  X  / t  u       ^ n v  1   5 �    Z P Z ]  V  &�    Z P Z  j  z {  G    |   R     ]*� $� �� }Y� M�N*� $-� K :+� \ � � :� �� � :,� � W� � ��� N-� i,�   S V j  +   :    ]  ^ 	 `  b  c   d ) e 2 f 5 g @ h I f S j W k [ m ,   R    ] - .     ] l m   L � �   ? � m    3 q r  2 ! � �  @ 	 s m  W  / t  u      L � �  1   1 	� +   Z � Z ] �  �    Z �  j  � {  G    �   �    �*� $� �*+� �M� }Y� N,� � :��� U � Z:�:*� $� K :� \ � � :� �Y� �:	� � � �	�� � � �W	�� � � �W	�� � � �W	�� � � �W	�� � � �W	�� � � �W	ù � � �W	͹ � � �W	ҹ � � �W	׹ � � �W	ܹ � � �W	� � � �W	� � � �W	� � � �W	� � � �W�:*� $� K :� \ � � :� }Y� :
� 
�� � � � W� � ���	
� �W-	� �� � W� f ��v� 
:� i-�  �� j  +   � (   t  u 	 w  x  { . | 2 } ? ~ I  R � [ � e � t � � � � � � � � � � � � � � � � � � �
 � �( �7 �F �J �W �a �j �s �v �� �� �� �� {� �� �� � ,   p   � - .    � l m  � n �  � � �  .w s m  2s � m  ?f q r  RS � �  [J 	s 2 � 
�  / t  u      � n �  � � s 2 � 
 1   t 	�    Z � �  V  �# 
  Z � � Z V Z ] � �  � / ��    Z � �  V  �    Z � �  j  {  G    |   G     `*� $� �� }Y� MN*� $-� K :+� \ � � :� ,�� � � � W� � ��� N� 5-� ;� ?,�   P S '  +   6    �  � 	 �  �  � ! � * � 3 � 6 � F � P � T � ^ � ,   H    ` - .     ` s m   O �   ; � m  ! / q r  3  � �  T 
 / 0  u      O �  1   1 	� ,   Z � Z ] �  �    Z �  '
 	  G   
    �     >�:(� :� O :
� 
� U �:	*	�
� f ����    +       �  �  � + � 1 � ; � ,   R    > - .     > l m    >    >    >  m   9!"   +# o  + $% 	 u      +#&  1    �  	  Z Z P  V       .    $*� $� �'M*� $,� K N-+�)� \ -+�,� \ -+�/� \ -+�2� \ -+�5� \ -+�8� \ -+�;�? -+�C� \ -	+�F�? -
+�I�? -+�L� \ -+�O� \ -+�R� \ -+�U� \ -+�X� \ -� b W[M+�]� � :� 5� U � Z:*� $,� K N-+�)� \ -� \ -� b W� f ��ǧ M,� i�   j  +   z    �  �  �  �  � " � - � 8 � C � N � Z � f � r � ~ � � � � � � � � � � � � � � � � � � � � � �
 � � � �# � ,   >   $ - .    $$%   � m   q r  � & � m   / t  1   , � �   Z ]  V  1�     j ` �     6     �    +       � ,        - .      l m  ab     @     �    +       ,         - .      l m    c m  d   ef   
  �g 	