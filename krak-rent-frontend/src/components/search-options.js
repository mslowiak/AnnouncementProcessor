const cityRegions = [
    { value: '-', label: 'Wszystko' },
    { value: 'Stare Miasto', label: 'Stare Miasto' },
    { value: 'Grzegórzki', label: 'Grzegórzki' },
    { value: 'Prądnik Czerwony', label: 'Prądnik Czerwony' },
    { value: 'Prądnik Biały', label: 'Prądnik Biały' },
    { value: 'Krowodrza', label: 'Krowodrza' },
    { value: 'Bronowice', label: 'Bronowice' },
    { value: 'Zwierzyniec', label: 'Zwierzyniec' },
    { value: 'Dębniki', label: 'Dębniki' },
    { value: 'Łagiewniki', label: 'Łagiewniki' },
    { value: 'Swoszowice', label: 'Swoszowice' },
    { value: 'Podgórze Duchackie', label: 'Podgórze Duchackie' },
    { value: 'Bieżanów-Prokocim', label: 'Bieżanów-Prokocim' },
    { value: 'Podgórze', label: 'Podgórze' },
    { value: 'Czyżyny', label: 'Czyżyny' },
    { value: 'Mistrzejowice', label: 'Mistrzejowice' },
    { value: 'Bieńczyce', label: 'Bieńczyce' },
    { value: 'Wzgórza Krzesławickie', label: 'Wzgórza Krzesławickie' },
    { value: 'Nowa Huta', label: 'Nowa Huta' }
  ];
  
  const lessor = [
    { value: '-', label: 'Wszystko' },
    { value: 'Właściciel', label: 'Właściciel' },
    { value: 'Agencja', label: 'Agencja' }
  ];
  
  const roomAmount = [
    { value: '-', label: 'Wszystko' },
    { value: '1', label: 'Kawalerka lub garsoniera' },
    { value: '2', label: '2 pokoje' },
    { value: '3', label: '3 pokoje' },
    { value: '4', label: '4 pokoje' },
    { value: '5', label: '5 pokoi' },
    { value: '6', label: '6 lub więcej' },
  ]
  
  const bathsAmount = [
    { value: '-', label: 'Wszystko' },
    { value: '1', label: '1 łazienka' },
    { value: '2', label: '2 łazienki' },
    { value: '3', label: '3 łazienki' },
    { value: '4', label: '4 lub więcej łazienek' },
  ]
  
  const trueOrFalse = [
    { value: '-', label: 'Wszystko' },
    { value: 'Tak', label: 'Tak' },
    { value: 'Nie', label: 'Nie' }
  ]
  
  export { cityRegions, lessor, roomAmount, bathsAmount, trueOrFalse }