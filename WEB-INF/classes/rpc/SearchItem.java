Ęūēž   4 Ŗ  rpc/SearchItem  javax/servlet/http/HttpServlet serialVersionUID J ConstantValue        <init> ()V Code
   
  LineNumberTable LocalVariableTable this Lrpc/SearchItem; doGet R(Ljavax/servlet/http/HttpServletRequest;Ljavax/servlet/http/HttpServletResponse;)V 
Exceptions  javax/servlet/ServletException  java/io/IOException  user_id    %javax/servlet/http/HttpServletRequest   ! getParameter &(Ljava/lang/String;)Ljava/lang/String; # lat
 % ' & java/lang/Double ( ) parseDouble (Ljava/lang/String;)D + lon - term
 / 1 0 db/DBConnectionFactory 2 3 getDBConnection ()Ldb/DBConnection; 5 7 6 db/DBConnection 8 9 searchItems 8(Ljava/lang/String;DDLjava/lang/String;)Ljava/util/List; ; java/util/ArrayList
 :  5 > ? @ getFavoriteItemIds #(Ljava/lang/String;)Ljava/util/Set; B D C java/util/List E F iterator ()Ljava/util/Iterator; H J I java/util/Iterator K L next ()Ljava/lang/Object; N entity/Item
 M P Q R toJSONObject ()Lorg/json/JSONObject; T favorite
 M V W X 	getItemId ()Ljava/lang/String; Z \ [ java/util/Set ] ^ contains (Ljava/lang/Object;)Z
 ` b a org/json/JSONObject c d put *(Ljava/lang/String;Z)Lorg/json/JSONObject; B f g ^ add H i j k hasNext ()Z
 m o n java/lang/Exception p  printStackTrace r org/json/JSONArray
 q t 
 u (Ljava/util/Collection;)V
 w y x rpc/RpcHelper z { writeJsonArray ?(Ljavax/servlet/http/HttpServletResponse;Lorg/json/JSONArray;)V request 'Ljavax/servlet/http/HttpServletRequest; response (Ljavax/servlet/http/HttpServletResponse; userId Ljava/lang/String; D conn Ldb/DBConnection; items Ljava/util/List; list Ljava/util/Set; item Lentity/Item; obj Lorg/json/JSONObject; e Ljava/lang/Exception; array Lorg/json/JSONArray; LocalVariableTypeTable Ljava/util/List<Lentity/Item;>; 'Ljava/util/List<Lorg/json/JSONObject;>; #Ljava/util/Set<Ljava/lang/String;>; StackMapTable  &javax/servlet/http/HttpServletResponse  java/lang/String doPost
     
SourceFile SearchItem.java RuntimeVisibleAnnotations %Ljavax/servlet/annotation/WebServlet; value /search !                 
      3     *ˇ ą       
    %  '                               Ŋ+š  N+"š  ¸ $9+*š  ¸ $9+,š  :¸ .:		-š 4 :
ģ :Yˇ <:	-š = :
š A :§ 9š G Ā M:ļ O:Æ Sļ Uš Y ļ _Wš e Wš h ˙Ã§ 
:ļ lģ qYˇ s:,¸ vą  U Ą ¤ m     N    . 	 /  0 # 1 - 3 2 4 B 5 K 7 U 9 m : t ; y <  >  9 Ą @ Ļ A Ģ C ļ D ŧ F        Ŋ       Ŋ | }    Ŋ ~   	 ´     § #   #  +   -  -   2    	 B {   
 K r    U h T   m *    t #    Ļ     ļ           B {   
 K r    U h T      § ˙ a       5 B B Z  H  ˙ +       5 B B Z M H `  ˙ 	       5 B B Z  H  ˙        5 B B Z  m               I     *+,ļ ą       
    P  Q                 | }     ~                 Ą[ s ĸ