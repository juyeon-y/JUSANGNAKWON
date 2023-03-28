import styles from "@/components/Playground/DrinkGuide/BeerGuide.module.css";

const BeerGuide = () => {
  return (
    <div className={`${styles[`container`]}`}>
      <h1 className={`${styles[`drinktype`]}`}>BEER</h1>
      <div className={`${styles[`content-wrap`]}`}>
        <h3 className={`${styles[`small-title`]}`}>맥주의 다양한 스타일</h3>
        <p>
          맥주는 다양한 스타일이 존재합니다. 에일, 라거, IPA, 스타우트 등 각각의 스타일은 맥주의 특징과 풍미가 다릅니다.
          처음 입문하시는 분이라면 여러 스타일을 시도해 보고, 어떤 스타일을 선호하는지 파악해보는 것이 좋습니다.
        </p>
        <h3 className={`${styles[`small-title`]}`}>입문자용 추천 맥주</h3>
        <p>근처 편의점에서 쉽게 구할 수 있는 맥주들이예요!</p>
        <br />
        <p>
          1. 데스페라도스 : 일반적인 라거 맥주와는 달리, 데스페라도스는 라거 맥주에 테킬라를 더한 혼합맥주입니다. 따라서
          특유의 테킬라 향과 맛이 느껴지며, 약간의 산미롸 쌉쌀한 맛도 느껴집니다. 병 뚜껑을 열면, 라임 향이 강조된
          특별한 아로마가 나오며, 일반적인 맥주보다도 높은 알코올 함량을 가지고 있습니다. 도수 높지만 향긋한 맥주가
          땡긴다면 추천!
        </p>
        <br />
        <p>
          코젤다크 : 체코의 코젤 맥주 회사에서 생산하는 Dark 스타일의 맥주입니다. 다른 일반적인 라거 맥주들이랑은 달리,
          다크 스타일의 맥주는 로스티한 색상과 진한 맛이 특징입니다. 코젤다크 맥주도 이와 같이 진한 갈색의 색상과,
          카라멜, 초콜릿, 커피 등의 풍부한 맛과 향이 느껴집니다. 코젤다크는 보통 유럽에서 만들어지는 더어 스타일의
          맥주와는 다르게, 덜 깊은 맛과 적당한 탄산감으로 더 부드러운 맛을 지니고 있어 맥주 애호가들에개 큰 인기를 끄는
          맥주 중 하나입니다.
        </p>
        <br />
        <p>
          타이거 레몬 : 싱가폴의 맥주 브랜드인 타이거가 만든 레몬 향의 퓨전 맥주입니다. 전통적인 라거 맥주에 청량감과
          상큼함을 더해준 제품으로, 레몬향과 함께 탄산감과 알코올 함량이 적당하게 조화를 이루어 부드럽고 깔끔한 맛을
          자랑합니다. 타이거레몬 맥주는 레몬과 같은 과일 향과 함께 깔끔하고 경쾌한 맛이 특징입니다. 맛 자체는 꽤 달지만,
          레몬의 신맛과 맥주 특유의 쓴 맛이 잘 조화되어 상쾌하게 느껴집니다. 또한, 타이거레몬 맥주는 낮은 알코올
          함량으로 여름철 더위를 식히는 청량감 있는 맥주로 많은 이들에게 인기가 있습니다.
        </p>
        <br />
        <p>
          홉하우스 : 국내에서 만들어지는 수제 맥주 브랜드 중 하나로, 홉의 향과 쓴 맛이 강조된 인상적인 맥주입니다.
          미국산 호프와 국내산 호프를 블랜딩하여 만든 제품으로, 비교적 강한 홉의 향과 함께 느껴지는 씁쓸하고 깔끔한 맛이
          특징입니다. 홉하우스 맥주는 호프의 향과 풍미를 강조한 맥주로, 씁쓸하면서도 상쾌한 맛이 느껴집니다. 특히, 맥주
          한잔을 즐기는 과정에서 느껴지는 홉의 향이 인상적이며, 홉의 씁쓸한 맛이 잘 조화되어 맛의 균형을 이루고
          있습니다. 또한, 홉하우스 맥주는 높은 알코올 함량으로 인해 강한 맛을 느끼기도 하지만, 그만큼 맛의 깊이가
          느껴지는 맥주로써, 맥주 팬들에게 인기가 많습니다.
        </p>
        <br />
        <p>
          버드와이저 : 미국의 대표적인 브루어리 회사인 안하이저-브츠(Anheuser-Busch)에서 생산하는 맥주입니다. 가벼운
          맛과 탄산감이 뛰어나며, 약간의 쓴맛과 함께 깔끔하고 매끄러운 맛이 특징입니다. 미국에서 가장 많이 판매되는 맥주
          중 하나로, 대중적인 인기를 끌고 있습니다.
        </p>
      </div>
    </div>
  );
};

export default BeerGuide;
