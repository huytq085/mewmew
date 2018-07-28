create table Persistent_Logins
(
  username  varchar(64)                         not null,
  series    varchar(64)                         not null
    primary key,
  token     varchar(64)                         not null,
  last_used timestamp default CURRENT_TIMESTAMP not null
  on update CURRENT_TIMESTAMP
);

create table SPRING_SESSION
(
  PRIMARY_ID            char(36)     not null
    primary key,
  SESSION_ID            char(36)     not null,
  CREATION_TIME         bigint       not null,
  LAST_ACCESS_TIME      bigint       not null,
  MAX_INACTIVE_INTERVAL int          not null,
  EXPIRY_TIME           bigint       not null,
  PRINCIPAL_NAME        varchar(100) null,
  constraint SPRING_SESSION_IX1
  unique (SESSION_ID)
);

create index SPRING_SESSION_IX2
  on SPRING_SESSION (EXPIRY_TIME);

create index SPRING_SESSION_IX3
  on SPRING_SESSION (PRINCIPAL_NAME);

create table SPRING_SESSION_ATTRIBUTES
(
  SESSION_PRIMARY_ID char(36)     not null,
  ATTRIBUTE_NAME     varchar(200) not null,
  ATTRIBUTE_BYTES    blob         not null,
  primary key (SESSION_PRIMARY_ID, ATTRIBUTE_NAME),
  constraint SPRING_SESSION_ATTRIBUTES_FK
  foreign key (SESSION_PRIMARY_ID) references SPRING_SESSION (PRIMARY_ID)
    on delete cascade
);

create table app_role
(
  role_id   bigint      not null
    primary key,
  role_name varchar(30) not null,
  constraint APP_ROLE_UK
  unique (role_name)
);

create table category
(
  id   int auto_increment
    primary key,
  name varchar(30) not null,
  constraint category_id_uindex
  unique (id)
);

create table hibernate_sequence
(
  next_val bigint null
)
  engine = MyISAM;

create table sequence_values
(
  id        int auto_increment
    primary key,
  thread_id int                                not null,
  created   datetime default CURRENT_TIMESTAMP null
);

create table tag
(
  id   int auto_increment
    primary key,
  name varchar(50) not null,
  constraint tag_id_uindex
  unique (id)
);

create table user
(
  id          int auto_increment
    primary key,
  username    varchar(30) charset utf8  not null,
  password    varchar(100)              not null,
  gender      varchar(10) charset utf8  not null,
  description longtext charset utf8     null,
  purpose     varchar(100) charset utf8 null,
  email       varchar(30) charset utf8  not null,
  phone       varchar(20)               null,
  status      varchar(10) default 'ACT' null,
  address     varchar(100) charset utf8 null,
  city        varchar(10) charset utf8  null,
  avatar      varchar(100) charset utf8 null,
  rate        int(5)                    null,
  height      int(3)                    null,
  weight      int(3)                    null,
  job         varchar(45) charset utf8  null,
  matrimony   varchar(10)               null,
  date_added  datetime                  null,
  full_name   varchar(255)              null,
  last_access datetime                  null,
  constraint username_UNIQUE
  unique (username),
  constraint email_UNIQUE
  unique (email)
)
  comment 'my dating - data store'
  collate = utf8_unicode_ci;

create table article
(
  id          int auto_increment
    primary key,
  user_id     int                                not null,
  subject     longtext                           not null,
  view        int default '0'                    not null,
  category_id int default '1'                    null,
  date_added  datetime default CURRENT_TIMESTAMP null,
  updated_at  datetime default CURRENT_TIMESTAMP null,
  content     longtext                           null,
  constraint article_user__fk
  foreign key (user_id) references user (id),
  constraint article_category__fk
  foreign key (category_id) references category (id)
)
  charset = utf8mb4;

create index article_category__fk
  on article (category_id);

create index article_user__fk
  on article (user_id);

create table article_tag
(
  id         int auto_increment
    primary key,
  article_id int null,
  tag_id     int null,
  constraint article_tag_id_uindex
  unique (id),
  constraint article_tag_article__fk
  foreign key (article_id) references article (id),
  constraint article_tag_tag__fk
  foreign key (tag_id) references tag (id)
);

create index article_tag_article__fk
  on article_tag (article_id);

create index article_tag_tag__fk
  on article_tag (tag_id);

create table comment
(
  article_id  int                                not null,
  content     longtext                           not null,
  num_of_like int default '0'                    null,
  date_added  datetime default CURRENT_TIMESTAMP null,
  id          int auto_increment
    primary key,
  user_id     int                                null,
  constraint fk_comment_article1
  foreign key (article_id) references article (id)
    on update cascade
    on delete cascade,
  constraint FK8kcum44fvpupyw6f5baccx25c
  foreign key (user_id) references user (id)
)
  collate = utf8_unicode_ci;

create index FK8kcum44fvpupyw6f5baccx25c
  on comment (user_id);

create index fk_comment_article1_idx
  on comment (article_id);

create table friendship
(
  follower_id  int                                 null,
  following_id int                                 null,
  date_added   timestamp default CURRENT_TIMESTAMP not null,
  constraint friendship_user_id_fk
  foreign key (follower_id) references user (id),
  constraint friendship_user_id_fks_2
  foreign key (following_id) references user (id)
);

create index friendship_user_id_fk_2
  on friendship (follower_id);

create index friendship_user_id_fks_2
  on friendship (following_id);

create table likes
(
  time       datetime default CURRENT_TIMESTAMP null,
  user_id    int                                not null,
  article_id int                                not null,
  constraint likes_user_id_article_id_pk
  unique (user_id, article_id),
  constraint fk_like_user1
  foreign key (user_id) references user (id),
  constraint fk_like_article1
  foreign key (article_id) references article (id)
);

create index fk_like_article1_idx
  on likes (article_id);

create index fk_like_user1_idx
  on likes (user_id);

create table message
(
  id           int auto_increment
    primary key,
  subject      varchar(45) charset utf8 not null,
  content      longtext                 not null,
  senderId     int                      not null,
  recipientId  int                      not null,
  date_added   datetime                 not null,
  name         varchar(255)             not null,
  recipient_id int                      not null,
  sender_id    int                      not null,
  constraint fk_message_user1
  foreign key (senderId) references user (id),
  constraint fk_message_user2
  foreign key (recipientId) references user (id),
  constraint FKiup8wew331d92o7u3k8d918o3
  foreign key (recipient_id) references user (id),
  constraint FKcnj2qaf5yc36v2f90jw2ipl9b
  foreign key (sender_id) references user (id)
)
  charset = utf8mb4;

create index FKcnj2qaf5yc36v2f90jw2ipl9b
  on message (sender_id);

create index FKiup8wew331d92o7u3k8d918o3
  on message (recipient_id);

create index fk_message_user1_idx
  on message (senderId);

create index fk_message_user2_idx
  on message (recipientId);

create table user_role
(
  id      bigint auto_increment
    primary key,
  user_id int    not null,
  role_id bigint not null,
  constraint user_role_UK
  unique (user_id, role_id),
  constraint user_role_FK1
  foreign key (user_id) references user (id)
    on delete cascade,
  constraint user_role_FK2
  foreign key (role_id) references app_role (role_id)
    on delete cascade
);

create index user_role_FK2
  on user_role (role_id);

create procedure comment(IN user int, IN article int, IN `_content` longtext, OUT isSuccess int(1))
  begin
    insert into comment (user_id, article_id, content) values (user, article, _content);
    set isSuccess = ROW_COUNT();
  end;

create procedure doLike(IN user int, IN article int, OUT isSuccess int(1))
  begin
    insert into likes (user_id, article_id) values (user, article);
    set isSuccess = ROW_COUNT();
  end;

create procedure follow(IN user1 int, IN user2 int, OUT isSuccess int(1))
  begin
    insert into friendship (follower_id, following_id) values (user1, user2);
    set isSuccess = ROW_COUNT();
  end;

create procedure isFollowing(IN user1 int, IN user2 int, OUT isFollowing int(1))
  begin
    set isFollowing = 0;
    IF EXISTS(select fs.date_added
              from friendship fs
              where fs.follower_id = user1 and fs.following_id = user2)
    THEN
      set isFollowing = 1;
    END IF;
  END;

create procedure isLike(IN user int, IN article int, OUT isLike int(1))
  begin
    set isLike = 0;
    IF EXISTS(select l.user_id
              from likes l
              where l.user_id = user and l.article_id = article)
    THEN
      set isLike = 1;
    END IF;
  end;

create procedure removeComment(IN user int, IN article int, OUT isSuccess int(1))
  begin
    delete from comment
    where userId = user and article_id = article;
    set isSuccess = ROW_COUNT();
  end;

create function sequence_nextval()
  returns int
  BEGIN
    DECLARE nextval INTEGER;
    INSERT INTO sequence_values (thread_id) VALUE (CONNECTION_ID());
    SELECT id
    FROM sequence_values
    ORDER BY created DESC
    LIMIT 1
    INTO nextval;
    RETURN nextval;
  END;

create procedure unFollow(IN user1 int, IN user2 int, OUT isSuccess int(1))
  begin
    delete from friendship
    where follower_id = user1 and following_id = user2
    limit 1;
    set isSuccess = ROW_COUNT();
  end;

create procedure unLike(IN user int, IN article int, OUT isSuccess int(1))
  begin
    delete from likes
    where user_id = user and article_id = article;
    set isSuccess = ROW_COUNT();
  end;


