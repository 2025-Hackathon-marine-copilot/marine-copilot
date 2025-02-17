# 지역문제 해결 해커톤

## 개요

&nbsp;부산 지역문제 해결을 위한 프로젝트를 진행한다. 과거 동북아 물류 허브로서 활발히 운영되던 부산항은 최근 글로벌 해운·물류 산업의 변화와 노후된 인프라, 자동화 부족, 항만 운영의 비효율성 등의 문제로 인해 경쟁력이 저하되고 있다. 이에 따라 AI 기반 항만 자동화 시스템을 구축하여 항만 운영 효율성을 극대화하고, 지역 경제를 활성화하며, 지속 가능한 항만 생태계를 조성하는 것을 목표로 한다.

&nbsp;이 프로젝트는 AI 자동 학습을 기반으로 하여 선박 입출항 및 화물 이동 최적화를 구현하는 데 초점을 맞춘다. 부산항의 스마트 항만 전환에 기여함으로써 부산의 항만 경쟁력을 강화하고 동북아 물류 허브로서의 입지를 공고히 하는 동시에, 지역 경제 부흥과 지역 소멸 문제 해결에도 기여한다.


### 참고 자료

- “부산항 스마트시스템 조기 구축” - [내일신문](https://www.naeil.com/news/read/537644)

- 부산항만공사, 부산항 스마트항만 전략 박차 - [뉴스핌](https://www.newspim.com/news/view/20250113001088)

- (이후 추가)


## 주요 기능

- 도선 업무 최적화

  -  부산항은 선박 입출항이 활발하게 이루어지는 항만으로, 도선사의 경험과 판단에 의해 선박이 안전하게 접안 및 출항할 수 있도록 운영된다. 하지만, 기상 변화, 조류, 타 선박과의 충돌 위험 등의 요인으로 인해 최적의 도선 경로를 수립하는 것이 쉽지 않다. 또한, 기존 도선 방식은 수작업 의존도가 높아 항만 운영의 효율성을 저하시킬 수 있다.
  
  - 이를 해결하기 위해 AI 기반 도선 업무 자동화 시스템을 도입하여 선박의 위치, 날씨, 지형 정보 등을 실시간으로 분석하고, 최적의 접안 위치 및 이동 경로를 자동으로 추천하는 기능을 구현한다. 강화학습 방식을 통해 제작한 AI 모델을 통해 실시간으로 변화하는 환경 요소를 반영하여 최적화된 도선 경로를 생성한다.
  
  - 이를 통해 도선사의 업무 부담을 줄이고, 선박 운항의 안전성과 효율성을 극대화할 수 있다. 나아가, 향후 자동 접안 및 자율 운항 시스템과의 연계를 통해 스마트 항만의 완전 자동화로 발전할 수 있는 가능성을 제공한다.

- 항만 물류 검수 자동화

  - 항만 물류 검수 과정은 수많은 컨테이너의 적재 및 반출입을 관리하는 중요한 절차이지만 현재 대부분의 검수 과정은 인력에 의존하고 있어 시간이 많이 소요되고, 오류 발생 가능성이 높으며, 물류 흐름에 지연을 초래할 수 있다.

  - AI 기반의 검수 자동화 시스템은 선석, 야드, 게이트에서 발생하는 검수 작업을 최적화하여 작업 생산성을 향상시키고, 오작업 및 안전사고를 예방하는 데 기여한다. 시스템은 선석, 야드, 게이트 등에서 다방면으로 촬영한 컨테이너의 정보에서 BIC Code 및 Type Size Code를 추출 후, 이를 통해 선적·하역 과정에서 발생할 수 있는 오류(오선적/오반출)를 최소화하고, 위험물 컨테이너 정보를 자동으로 확인하여 하역 작업의 안전성을 확보한다.

  - AI 기반 물류 검수 자동화 시스템은 항만 운영의 신뢰도를 높이고, 검수 시간을 단축하여 물류 흐름을 원활하게 유지하는 데 기여할 것이다.


## 핵심 기술 스택

- Spring Boot

- PyTorch, Tensorflow

- 기타 등등...


## 인터페이스

![인터페이스_자료_1](https://github.com/nacho2407/marine_copilot/blob/main/etc/img/interface_1.jpg)

![인터페이스_자료_2](https://github.com/nacho2407/marine_copilot/blob/main/etc/img/interface_2.jpg)


## 데이터 흐름

[Canva Link](https://www.canva.com/design/DAGe4qcUVzM/9yD7shNlYk-8l3d44qNUvQ/view?utm_content=DAGe4qcUVzM&utm_campaign=designshare&utm_medium=link2&utm_source=uniquelinks&utlId=h13892caa96)


## 메모

:)
