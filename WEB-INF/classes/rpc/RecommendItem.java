����   4 |  rpc/RecommendItem  javax/servlet/http/HttpServlet serialVersionUID J ConstantValue        <init> ()V Code
   
  LineNumberTable LocalVariableTable this Lrpc/RecommendItem; doGet R(Ljavax/servlet/http/HttpServletRequest;Ljavax/servlet/http/HttpServletResponse;)V 
Exceptions  javax/servlet/ServletException  java/io/IOException  user_id    %javax/servlet/http/HttpServletRequest   ! getParameter &(Ljava/lang/String;)Ljava/lang/String; # lat
 % ' & java/lang/Double ( ) parseDouble (Ljava/lang/String;)D + lon - algorithm/GeoRecommendation
 , 
 , 0 1 2 recommendItems &(Ljava/lang/String;DD)Ljava/util/List; 4 org/json/JSONArray
 3  7 9 8 java/util/List : ; iterator ()Ljava/util/Iterator; = ? > java/util/Iterator @ A next ()Ljava/lang/Object; C entity/Item
 B E F G toJSONObject ()Lorg/json/JSONObject;
 3 I J K put ((Ljava/lang/Object;)Lorg/json/JSONArray; = M N O hasNext ()Z
 Q S R java/lang/Exception T  printStackTrace
 V X W rpc/RpcHelper Y Z writeJsonArray ?(Ljavax/servlet/http/HttpServletResponse;Lorg/json/JSONArray;)V request 'Ljavax/servlet/http/HttpServletRequest; response (Ljavax/servlet/http/HttpServletResponse; userId Ljava/lang/String; D recommendation Lalgorithm/GeoRecommendation; items Ljava/util/List; result Lorg/json/JSONArray; item Lentity/Item; e Ljava/lang/Exception; LocalVariableTypeTable Ljava/util/List<Lentity/Item;>; StackMapTable p &javax/servlet/http/HttpServletResponse r java/lang/String doPost
  u   
SourceFile RecommendItem.java RuntimeVisibleAnnotations %Ljavax/servlet/annotation/WebServlet; value /recommendation !                 
      3     *� �       
      !                          �     +�  N+"�  � $9+*�  � $9� ,Y� .:-� /:	� 3Y� 5:
	� 6 :� � < � B:
� D� HW� L ��� 
:� P,
� U�  A n q Q     6    ' 	 (  ) # * , + 8 - A / Y 0 d / n 2 s 3 x 5 ~ 6    p            [ \     ] ^  	 v _ `   i # a  # \ + a  , S b c  8 G d e 	 A > f g 
 Y  h i  s  j k  l     8 G d m 	 n   G � M    o q , 7 3  =  �  	   o q , 7 3  Q  s             I     *+,� t�       
    =  >                 [ \     ] ^   v    w x     y  z[ s {