����   4�  external/TicketMasterAPI  java/lang/Object  external/ExternalAPI API_HOST Ljava/lang/String; ConstantValue  app.ticketmaster.com SEARCH_PATH  /discovery/v2/events.json DEFAULT_TERM    API_KEY   nNsUiZfcY01uiNKuktnC4eWtjWXoUHR0 <init> ()V Code
     LineNumberTable LocalVariableTable this Lexternal/TicketMasterAPI; search &(DDLjava/lang/String;)Ljava/util/List; 	Signature 5(DDLjava/lang/String;)Ljava/util/List<Lentity/Item;>; # 4http://app.ticketmaster.com/discovery/v2/events.json
 % ' & external/GeoHash ( ) encodeGeohash (DDI)Ljava/lang/String;
  + , - urlEncodeHelper &(Ljava/lang/String;)Ljava/lang/String; / *apikey=%s&geoPoint=%s&keyword=%s&radius=50
 1 3 2 java/lang/String 4 5 format 9(Ljava/lang/String;[Ljava/lang/Object;)Ljava/lang/String; 7 java/net/URL 9 java/lang/StringBuilder
 1 ; < = valueOf &(Ljava/lang/Object;)Ljava/lang/String;
 8 ?  @ (Ljava/lang/String;)V B ?
 8 D E F append -(Ljava/lang/String;)Ljava/lang/StringBuilder;
 8 H I J toString ()Ljava/lang/String;
 6 ?
 6 M N O openConnection ()Ljava/net/URLConnection; Q java/net/HttpURLConnection S GET
 P U V @ setRequestMethod
 P X Y Z getResponseCode ()I	 \ ^ ] java/lang/System _ ` out Ljava/io/PrintStream; b  
Sending 'GET' request to URL : 
 d f e java/io/PrintStream g @ println i Response Code : 
 8 k E l (I)Ljava/lang/StringBuilder; n java/io/BufferedReader p java/io/InputStreamReader
 P r s t getInputStream ()Ljava/io/InputStream;
 o v  w (Ljava/io/InputStream;)V
 m y  z (Ljava/io/Reader;)V
 8 
 m } ~ J readLine
 m � �  close � org/json/JSONObject
 � ? � 	_embedded
 � � � � get &(Ljava/lang/String;)Ljava/lang/Object; � events � org/json/JSONArray
  � � � getItemList &(Lorg/json/JSONArray;)Ljava/util/List;
 � � � java/lang/Exception �  printStackTrace lat D lon term url geoHash query 
connection Ljava/net/HttpURLConnection; responseCode I in Ljava/io/BufferedReader; 	inputLine response Ljava/lang/StringBuilder; responseJson Lorg/json/JSONObject; object array Lorg/json/JSONArray; e Ljava/lang/Exception; StackMapTable � UTF-8
 � � � java/net/URLEncoder � � encode 8(Ljava/lang/String;Ljava/lang/String;)Ljava/lang/String; queryAPI (DD)V
  �   � � � java/util/List � � iterator ()Ljava/util/Iterator; � � � java/util/Iterator � � next ()Ljava/lang/Object; � entity/Item
 � � � � toJSONObject ()Lorg/json/JSONObject;
 d � g � (Ljava/lang/Object;)V � � � � hasNext ()Z itemList Ljava/util/List; item Lentity/Item; 
jsonObject LocalVariableTypeTable Ljava/util/List<Lentity/Item;>; 
Exceptions � org/json/JSONException 5(Lorg/json/JSONArray;)Ljava/util/List<Lentity/Item;>; � java/util/ArrayList
 � 
 � � � � getJSONObject (I)Lorg/json/JSONObject; � entity/Item$ItemBuilder
 �  � id
  � � � getStringFieldOrNull ;(Lorg/json/JSONObject;Ljava/lang/String;)Ljava/lang/String;
 � � � � 	setItemId -(Ljava/lang/String;)Lentity/Item$ItemBuilder; � name
 � � � � setName
  � � � getDescription )(Lorg/json/JSONObject;)Ljava/lang/String;
 � � � � setDescription
  getCategories &(Lorg/json/JSONObject;)Ljava/util/Set;
 � setCategories *(Ljava/util/Set;)Lentity/Item$ItemBuilder;
 	
 � getImageUrl
 � � setImageUrl �
 � � setUrl
  getVenue ,(Lorg/json/JSONObject;)Lorg/json/JSONObject; address
 � isNull (Ljava/lang/String;)Z
 � � )(Ljava/lang/String;)Lorg/json/JSONObject;  line1
 �"# - 	getString% line2' line3
 �)* � 
setAddress, city
 �./ � setCity1 country
 �34 � 
setCountry6 state
 �89 � setState; 
postalCode
 �=> � 
setZipcode@ locationB latitude
 DEF getNumericFieldOrNull *(Lorg/json/JSONObject;Ljava/lang/String;)D
 �HIJ setLatitude (D)Lentity/Item$ItemBuilder;L 	longitude
 �NOJ setLongitude
 �QRS build ()Lentity/Item; �UVW add (Ljava/lang/Object;)Z
 �YZ Z length i event builder Lentity/Item$ItemBuilder; venue sbb venues
 �def getJSONArray ((Ljava/lang/String;)Lorg/json/JSONArray; embeddedi images imagesArray :(Lorg/json/JSONObject;)Ljava/util/Set<Ljava/lang/String;>;m java/util/HashSet
l p classificationsr segmenttUu java/util/Set 
categories Ljava/util/Set; j classification #Ljava/util/Set<Ljava/lang/String;>;| description~ additionalInfo� info� 
pleaseNote field
 ���� 	getDouble (Ljava/lang/String;)D main ([Ljava/lang/String;)V
  @I�n;F�����Q��
 � � � args [Ljava/lang/String; tmApi 
SourceFile TicketMasterAPI.java InnerClasses ItemBuilder !          	    
     	         	         	             /     *� �                                 !   �    ":')� $:� :*� *:.� YSYSYS� 0:� 6Y� 8Y� :� >A� C� C� G� K� L� P:		R� T	� W6
� [� 8Ya� >� CA� C� C� G� c� [� 8Yh� >
� j� G� c� mY� oY	� q� u� x:� 8Y� {:� � CW� |Y:���� � �Y� G� �:�� �� �:�� �� �:*� ��:		� ��  7
 �     b            "  $ 7 ( _ * f / m 0 � 1 � 4 � 6 � 7 � 8 � 7 � : � < � = � > ? @ A C    �          � �    � �    �    �    �   7 � �   _ � � � 	 m � � � 
 � Q � �  �  �   � 5 �   � H � �  �  � �  �  � �   � �   � � 	 �   n �  1 1� �   1 1 1 1 P m 1 8  �    1 1 1 1 P m  8  � <   1 1 1 1  �  , -     p     +�� �L� M,� �+�     
 �         H  I  J  L                 �     � �  �    J �  � �      	   E*')� �:� � :� � � � �:� �:� [� �� � ��ާ 
:� ��  	 : = �     "    O 	 Q ! R ( S 0 Q : U ? V D X    H    E       E � �    E � �  	 < � �  !  � �  (  � �  ?  � �  �     	 < � �  �   ) �    �  �  �    �  �  � �  �     �      �   L  	  �� �Y� �M>��+� �:� �Y� �:*� � �W*�� � �W*� �� �W*� �W*��W*� �W*�:�"�� l�:� 8Y� {:�� �!� CW$�� $�!� CW&�� &�!� CW� G�(W+�� +�:*�� �-W0�� 0�:*�� �2W5�� 5�:*�� �7W*:� �<W?�� +?�:*A�C�GW*K�C�MW�P:,�T W�+�X��],�       � (   [  ]  ^  _  ` + a 9 b E c Q d ] e l f t g y h � i � j � k � l � n � o � q � r � t � v � w x z {% |3 ~> H �V �e �p �z �� �� �� �� ]� �    �   �      � � �  � � �  
�[ �  �\ �  �]^  t4_ �  � _ �  � V` �  , � % 1 � H 6 � z @ � � 	 � �  �     � � �  �   7 
�  �� � 	  � � � � � � 8  � 
"""� A�    �     �    �     2+��� *+��M,a�� ,a�cN-�X� 	-� ��           � 	 �  �  � " � * � 0 �    *    2       2\ �    g �  " b �  �    0 
 �  �     �    �     )+h��  +h�cM,�X� *,� �� ��           � 
 �  �  � ' �         )       )\ �   j �  �    '   �     �     k    �     Q�lY�nM+o�� @+o� �� �N6� &-� �:q�:,��!�s W�-�X���,�       & 	   �  �  �  � # � + � 5 � C � O �    H    Q       Q\ �   Ivw   2p �    /x �  + y �  5 r �  �      Ivz  �    � #t �"�   � �  �     �    �     J+{�� +{�!�+}�� +}�!�+�� +�!�+��� +��!��       & 	   � 
 �  �  � $ � . � 6 � @ � H �        J       J\ �  �      � �  �     �    ]     +,�� � +,�!�           �                \ �    �   �    D 1 EF  �     �    [     +,�� � +,���           �                \ �    �   �    D 	��     O     � Y��L+�����           �  �  �        ��    �   �   ��   
  � �� 	