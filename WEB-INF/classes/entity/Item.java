����   4x  entity/Item  java/lang/Object itemId Ljava/lang/String; name rating D address city country state zipcode latitude 	longitude description 
categories Ljava/util/Set; 	Signature #Ljava/util/Set<Ljava/lang/String;>; imageUrl url snippet 
snippetUrl hashCode ()I Code	    
   " ! java/lang/String  	  $   LineNumberTable LocalVariableTable this Lentity/Item; prime I result StackMapTable equals (Ljava/lang/Object;)Z
  0 1 2 getClass ()Ljava/lang/Class;
   4 - . obj Ljava/lang/Object; other <init> (Lentity/Item$ItemBuilder;)V
  ; 8 < ()V
 > @ ? entity/Item$ItemBuilder A B access$0 -(Lentity/Item$ItemBuilder;)Ljava/lang/String;
 > D E B access$1	  G  
 > I J K access$2 (Lentity/Item$ItemBuilder;)D	  M  	
 > O P B access$3	  R 
 
 > T U B access$4	  W  
 > Y Z B access$5	  \  
 > ^ _ B access$6	  a  
 > c d B access$7
 > f g K access$8	  i  	
 > k l K access$9	  n  	
 > p q B 	access$10	  s  
 > u v w 	access$11 *(Lentity/Item$ItemBuilder;)Ljava/util/Set;	  y  
 > { | B 	access$12	  ~  
 > � � B 	access$13	  �  
 > � � B 	access$14	  �  
 > � � B 	access$15	  �   builder Lentity/Item$ItemBuilder; 	getItemId ()Ljava/lang/String; getName 	getRating ()D 
getAddress getCity 
getCountry getState 
getZipcode getLatitude getLongitude getDescription getCategories ()Ljava/util/Set; %()Ljava/util/Set<Ljava/lang/String;>; getImageUrl getUrl 
getSnippet getSnippetUrl toJSONObject ()Lorg/json/JSONObject; � org/json/JSONObject
 � ; � item_id
 � � � � put ;(Ljava/lang/String;Ljava/lang/Object;)Lorg/json/JSONObject;  
 � � � � *(Ljava/lang/String;D)Lorg/json/JSONObject; 
         � org/json/JSONArray
 � � 8 � (Ljava/util/Collection;)V � 	image_url  � snippet_url 
 � � � org/json/JSONException � < printStackTrace Lorg/json/JSONObject; e Lorg/json/JSONException; getItemList &(Lorg/json/JSONArray;)Ljava/util/List; 
Exceptions 5(Lorg/json/JSONArray;)Ljava/util/List<Lentity/Item;>; � java/util/ArrayList
 � ;
 � � � � getJSONObject (I)Lorg/json/JSONObject;
 > ; � id
  � � � getStringFieldOrNull ;(Lorg/json/JSONObject;Ljava/lang/String;)Ljava/lang/String;
 > � � � 	setItemId -(Ljava/lang/String;)Lentity/Item$ItemBuilder;
 > � � � setName
  � � � )(Lorg/json/JSONObject;)Ljava/lang/String;
 > � � � setDescription
  � � � &(Lorg/json/JSONObject;)Ljava/util/Set;
 > � � � setCategories *(Ljava/util/Set;)Lentity/Item$ItemBuilder;
  � � �
 > � � � setImageUrl
 > � � � setUrl
  �  getVenue ,(Lorg/json/JSONObject;)Lorg/json/JSONObject;
 � isNull (Ljava/lang/String;)Z
 � � )(Ljava/lang/String;)Lorg/json/JSONObject;
 java/lang/StringBuilder
	 ; line1
 � 	getString &(Ljava/lang/String;)Ljava/lang/String;
	 append -(Ljava/lang/String;)Ljava/lang/StringBuilder; line2 line3
	 � toString
 > � 
setAddress
 >!" � setCity
 >$% � 
setCountry
 >'( � setState* 
postalCode
 >,- � 
setZipcode/ location
 123 getNumericFieldOrNull *(Lorg/json/JSONObject;Ljava/lang/String;)D
 >567 setLatitude (D)Lentity/Item$ItemBuilder;
 >9:7 setLongitude
 ><=> build ()Lentity/Item;@BA java/util/ListC . add
 �EF  length events Lorg/json/JSONArray; itemList Ljava/util/List; i event venue sb Ljava/lang/StringBuilder; item LocalVariableTypeTable Ljava/util/List<Lentity/Item;>;T 	_embeddedV venues
 �XYZ getJSONArray ((Ljava/lang/String;)Lorg/json/JSONArray; embedded :(Lorg/json/JSONObject;)Ljava/util/Set<Ljava/lang/String;>;^ java/util/HashSet
] ;a classifications
 �cde get &(Ljava/lang/String;)Ljava/lang/Object;g additionalInfoi infok 
pleaseNote field
 �nop 	getDouble (Ljava/lang/String;)D )(Lentity/Item$ItemBuilder;Lentity/Item;)V
 s 8 9 
SourceFile 	Item.java InnerClasses ItemBuilder !                  	    
                          	     	                                             �     7<=h*� � � 
*� � `=h*� #� � 
*� #� `=�    %              5  &        7 ' (    4 ) *   2 + *  ,   - �    �    P�      - .     �     a*+� �+� �*� /+� /� �+� M*� � ,� � �*� ,� � 3� �*� #� ,� #� �*� #,� #� 3� ��    %   J                    & ! - " / # = $ ? % F & M ' O ( ] ) _ * &        a ' (     a 5 6   B 7 (  ,    �    8 9     �     �*� :*+� =� *+� C� F*+� H� L*+� N� Q*+� S� V*+� X� [*+� ]� `*+� b� #*+� e� h*+� j� m*+� o� r*+� t� x*+� z� }*+� � �*+� �� �*+� �� ��    %   J    �  �  �  �  � $ � , � 4 � < � D � L � T � \ � d � l � t � | � � � &       � ' (     � � �   � �     /     *� �    %       � &        ' (    � �     /     *� F�    %       � &        ' (    � �     /     *� L�    %       � &        ' (    � �     /     *� Q�    %       � &        ' (    � �     /     *� V�    %       � &        ' (    � �     /     *� [�    %       � &        ' (    � �     /     *� `�    %       � &        ' (    � �     /     *� #�    %       � &        ' (    � �     /     *� h�    %       � &        ' (    � �     /     *� m�    %       � &        ' (    � �     /     *� r�    %       � &        ' (    � �      �    /     *� x�    %       � &        ' (    � �     /     *� }�    %       � &        ' (    � �     /     *� ��    %       � &        ' (    � �     /     *� ��    %       � &        ' (    � �     /     *� ��    %       � &        ' (    � �    t     ɻ �Y� �L+�*� � �W+�*� F� �W+�*� L� �W+�*� Q� �W+�*� V� �W+�*� [� �W+�*� `� �W+�*� #� �W+�*� h� �W+�*� m� �W+�*� r� �W+�� �Y*� x� �� �W+�*� }� �W+�*� �� �W+�*� �� �W+�*� �� �W� M,� �+�   � � �  %   R    �  �  �  � ) � 4 � ? � J � U � ` � k � v � � � � � � � � � � � � � � � � � &        � ' (    � 5 �  �  � �  ,    � �   �  �  � �  �     �     �   ?  	  �� �Y� �M>��+� �:� >Y� �:*ܷ ޶ �W*�� ޶ �W*� � �W*� � �W*� �� �W*ķ ޶ �W*� �:���� k��:�	Y�:�� ��W�� ��W�� ��W��W��� ��:*�� ޶ W��� ��:*�� ޶#W��� ��:*�� ޶&W*)� ޶+W.�� ).�:*��0�4W*��0�8W�;:,�? W�+�D��h,�    %   � (       + 9	 E
 Q ] k s x � � � � � � � � � � � �!"#,%6&?'M)\*g+q,-�2�3��6 &   �   � ' (    �GH  �IJ  
�K *  �L �  � � �  s*M �  � _ 
 �  � VNO  �   �    � ?   � q / � � 	P ( Q     �IR  ,   5 
� @� � 	  �@ � > � �	  � 
   ?�     �     �    �     4+S�� ++S�M,U�� ,U�WN-�D� 	-� װ�    %      ; 
< = > $? ,@ 2D &   *    4 ' (     4L �    [ �  $ VH  ,    2  � �  �     �    6     �    %      H &        ' (     L �   � �  �     �    \    w     �]Y�_M+`�b� �N,�    %      L M O &   *     ' (     L �        aH Q           � �  �     �    �     H+��� 
+���+f�� +f��+h�� +h��+j�� +j���    %   & 	  S 	T U V "W ,X 4Y >Z F\ &       H ' (     HL �  ,      � �  �     �    ]     +,�� � +,��    %      ` &         ' (     L �    l   ,    D   23  �     �    [     +,�� � +,�m�    %      d &         ' (     L �    l   ,    D  8q     &     *+�r�    %       � &      t   uv   
  > w 	