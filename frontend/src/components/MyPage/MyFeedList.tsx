import styles from "./MyFeedList.module.css";
import MyFeedItem from "./MyFeedItem";
import { useLocation, Link } from "react-router-dom";
import AddCircleIcon from "@mui/icons-material/AddCircle";

interface MyFeedItem {
  id: number;
  ratings: number | null;
  date: string;
  classification: string;
  alcoholType: string | null;
  img: string;
  content: string;
}

interface MyFeedListProps {
  myFeedListProps: MyFeedItem[];
  selectedDate: Date;
}

const MyFeedList = ({ myFeedListProps, selectedDate }: MyFeedListProps) => {
  const { pathname } = useLocation();
  const filteredPosts = myFeedListProps
    ? myFeedListProps.filter((feed) => {
        return new Date(feed.date).toDateString() === selectedDate.toDateString();
      })
    : [];

  return (
    <div className={`${styles[`myfeed-container`]}`}>
      <div className={`${styles[`myfeed-title`]}`}>
        <h2>내가 쓴 게시글</h2>
        {pathname === "/mypage" && (
          <Link to="../write/review">
            <AddCircleIcon fontSize="large" sx={{ color: "white" }}></AddCircleIcon>
          </Link>
        )}
      </div>
      {pathname === "/mypage/feed"
        ? myFeedListProps.map((myfeed) => {
            return <MyFeedItem key={myfeed.id} myfeed={myfeed}></MyFeedItem>;
          })
        : filteredPosts.map((myfeed) => {
            return <MyFeedItem key={myfeed.id} myfeed={myfeed}></MyFeedItem>;
          })}
    </div>
  );
};

export default MyFeedList;
