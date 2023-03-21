import React from "react";
import { Link } from "react-router-dom";
import styles from "./RecipeFeed.module.css";
import FavoriteBorderIcon from "@mui/icons-material/FavoriteBorder";

interface RecipedList {
  recipeList: {
    id: number;
    img: string;
    name: string;
    likes: number;
  }[];
}

export default function RecipeFeed(props: RecipedList) {
  return (
    <div className={`${styles[`drink-list-wrap`]}`}>
      <ul className={`${styles[`tab-drink-list`]}`}>
        {props.recipeList.map((item) => (
          <li key={item.id}>
            <div className={styles["item-container"]}>
              <Link to={`${item.id}`}>
              <img src={item.img}></img>
              </Link>
              <div className={styles["item-title"]}>
                <div>{item.name}</div>
                <div className={styles["like-box"]}>
                  <FavoriteBorderIcon />
                  {item.likes}
                </div>
              </div>
            </div>
          </li>
        ))}
      </ul>
    </div>
  );
}