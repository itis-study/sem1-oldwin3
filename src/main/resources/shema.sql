UPDATE users SET recipenumber=1 WHERE name='kirill antonov';

ALTER TABLE users add COLUMN ratingCount int;
ALTER TABLE voted add COLUMN recipeRating float;
ALTER TABLE voted add COLUMN creatorID uuid;
ALTER TABLE comments add COLUMN creatorName text;
ALTER TABLE communities add COLUMN usersNumber int;

ALTER TABLE communities
    ADD CONSTRAINT creatorid
        FOREIGN KEY (creatoruserid) REFERENCES users (id);
DELETE FROM communities WHERE id='646e8204-c458-4a12-b311-ed2eae30b0ac';
UPDATE communities SET rating=2.25  WHERE (id) = '5fa5d91c-9dda-4c3b-814a-6c550e8f933c';
UPDATE users SET rating=3  WHERE (name) = 'Oldwin4';

/*UPDATE recipes SET rating=0, ratingcount=0 WHERE id='887b2b89-027f-4233-ba1d-ea08ca7e3eea';
UPDATE recipes SET rating=0, ratingcount=0 WHERE id='5d6b77c8-b609-40c9-a7f6-d4d2203e7c23';
UPDATE recipes SET rating=0, ratingcount=0 WHERE id='87ac0a13-2005-4d21-a620-df1cc0c82d09';
UPDATE users SET rating=0 WHERE id='3b29811c-4839-4554-acea-cbd138cdad2e';
UPDATE users SET rating=0 WHERE id='dfa0b20e-e96a-4a9b-bc87-0effcdc3ae5d';
DELETE FROM voted WHERE userid='3b29811c-4839-4554-acea-cbd138cdad2e';
DELETE FROM voted WHERE userid='dfa0b20e-e96a-4a9b-bc87-0effcdc3ae5d';
DELETE FROM voted WHERE userid='0552d0f8-e3b1-4706-a803-cca8a9ebaa50';*/


DELETE FROM comments WHERE id='819e9458-3a64-496e-9ace-178693d9eb6c';
DELETE FROM comments WHERE id='d16456fa-25ef-4b61-a213-c5e248bf97cd';
DELETE FROM comments WHERE id='d68edbc4-2d7a-4825-a46c-b67b2d07b91f';

SELECT * FROM users where recipenumber != '0';
SELECT reciperating FROM voted WHERE userid='3b29811c-4839-4554-acea-cbd138cdad2e';

CREATE TABLE user_community (
                                user_id UUID,
                                community_id UUID,
                                PRIMARY KEY (user_id, community_id)
);

CREATE TABLE user_author (
                                author_id UUID,
                                user_id UUID,
                                PRIMARY KEY (author_id, user_id)
);

UPDATE users SET recipenumber=2  WHERE (id) = '3b29811c-4839-4554-acea-cbd138cdad2e';
DELETE FROM communities WHERE id='4208ede8-3ef1-4a6f-a80d-43fb640ced2e';
DELETE FROM user_community WHERE community_id='4208ede8-3ef1-4a6f-a80d-43fb640ced2e';

DELETE FROM recipes WHERE id='d8a91935-9c7a-4918-886a-1704d8b499f4';


ALTER TABLE communities
    DROP COLUMN rating;

UPDATE users SET password='03ac674216f3e15c761ee1a5e255f067953623c8b388b4459e13f978d7c846f4'  WHERE (name) = 'old';


ALTER TABLE comments
    DROP CONSTRAINT comments_recipeid_fkey,
    ADD CONSTRAINT comments_recipeid_fkey
        FOREIGN KEY (recipeid)
            REFERENCES recipes (id)
            ON DELETE CASCADE;

ALTER TABLE comments
    DROP CONSTRAINT comments_userid_fkey,
    ADD CONSTRAINT comments_userid_fkey
        FOREIGN KEY (userid)
            REFERENCES users (id)
            ON DELETE CASCADE;

ALTER TABLE voted
    DROP CONSTRAINT voted_userid_fkey,
    ADD CONSTRAINT voted_userid_fkey
        FOREIGN KEY (userid)
            REFERENCES users (id)
            ON DELETE CASCADE;

ALTER TABLE voted
    DROP CONSTRAINT voted_recipeid_fkey,
    ADD CONSTRAINT voted_recipeid_fkey
        FOREIGN KEY (recipeid)
            REFERENCES recipes (id)
            ON DELETE CASCADE;

ALTER TABLE communities
    DROP CONSTRAINT creatorid,
    ADD CONSTRAINT creatorid
        FOREIGN KEY (creatoruserid)
            REFERENCES users (id)
            ON DELETE CASCADE;

SELECT constraint_name, constraint_type
FROM information_schema.table_constraints
WHERE table_name = 'communities';

SELECT * FROM recipes LIMIT 1 OFFSET 0;