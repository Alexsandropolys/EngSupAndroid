-- phpMyAdmin SQL Dump
-- version 4.6.5.2
-- https://www.phpmyadmin.net/
--
-- Хост: 127.0.0.1:3306
-- Время создания: Фев 12 2017 г., 09:08
-- Версия сервера: 5.5.53
-- Версия PHP: 5.5.38

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- База данных: `project`
--

-- --------------------------------------------------------

--
-- Структура таблицы `progress`
--

CREATE TABLE `progress` (
  `userId` int(11) NOT NULL,
  `wordId` int(11) NOT NULL,
  `points` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=cp1251;

-- --------------------------------------------------------

--
-- Структура таблицы `unit`
--

CREATE TABLE `unit` (
  `id` int(11) NOT NULL,
  `name` varchar(20) NOT NULL,
  `startBookId` int(11) NOT NULL,
  `finishBookId` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=cp1251;

--
-- Дамп данных таблицы `unit`
--

INSERT INTO `unit` (`id`, `name`, `startBookId`, `finishBookId`) VALUES
(1, 'first unit', 1, 30);

-- --------------------------------------------------------

--
-- Структура таблицы `users`
--

CREATE TABLE `users` (
  `id` int(11) NOT NULL,
  `login` varchar(20) NOT NULL,
  `password` varchar(20) NOT NULL,
  `isAdmin` tinyint(1) NOT NULL DEFAULT '0',
  `unit` int(11) NOT NULL,
  `whoIs` varchar(50) NOT NULL,
  `group` varchar(5) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=cp1251;

--
-- Дамп данных таблицы `users`
--

INSERT INTO `users` (`id`, `login`, `password`, `isAdmin`, `unit`, `whoIs`, `group`) VALUES
(1, 'vav', '123', 0, 1, 'Slava', '8K51'),
(2, 'sas', 'asda', 0, 1, 'sadasd', '8k51');

-- --------------------------------------------------------

--
-- Структура таблицы `words`
--

CREATE TABLE `words` (
  `num` int(11) NOT NULL,
  `lang1` varchar(50) CHARACTER SET utf8 NOT NULL,
  `def1` varchar(50) CHARACTER SET utf8 NOT NULL,
  `lang2` varchar(50) CHARACTER SET utf8 NOT NULL,
  `def2` varchar(45) CHARACTER SET utf8 NOT NULL,
  `synid` int(11) DEFAULT NULL,
  `oppid` int(11) DEFAULT NULL,
  `pos` varchar(45) CHARACTER SET utf8 NOT NULL,
  `sent1` text CHARACTER SET utf8 NOT NULL,
  `sent2` text CHARACTER SET utf8 NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=cp1251 ROW_FORMAT=COMPACT;

--
-- Дамп данных таблицы `words`
--

INSERT INTO `words` (`num`, `lang1`, `def1`, `lang2`, `def2`, `synid`, `oppid`, `pos`, `sent1`, `sent2`) VALUES
(0, '', '', '', '', 0, 0, '', '', ''),
(1, 'bias', 'inclination for or against', 'предвзятость', '', 11, 0, 'n', 'The townspeople show a bias in favour of French habits and fashions.', 'Numbers ending in \"5\" have been rounded to the nearest multiple of 20 to prevent systematic bias.'),
(2, 'breach', 'an act of breaking, esp. an agreement', 'нарушение', '', 15, 0, 'n', '\"We have a security breach,\" the general said, his voice unsteady.', 'A breach of the covenant to repair gives the landlord an action for damages'),
(3, 'displease', 'cause bad feelings', 'раздражать', '', 9, 0, 'v', 'Low returns will surely displease our investors.', ''),
(4, 'enhance', 'increase, intensify, improve', 'усиливать', '', 0, 0, 'v', 'We need to enhance this equipment to be useful.', ''),
(5, 'explicitly', 'openly, directly', 'прямо', '', 0, 0, 'adv', 'The contract explicitly mentioned a need for renovation.', ''),
(6, 'inappropriate', 'not good for the situation', 'неподходящий', '', 0, 0, 'adj', 'This word may be inappropriate here.', ''),
(7, 'maintain', 'support', 'поддерживать', '', 0, 0, 'v', 'This equipment helps maintain life in the patient.', ''),
(8, 'menace', 'smth or smb likely to cause harm', 'угроза', '', 0, 0, 'n', 'McDonalds\' is not a menace to our business, we are in a different segment.', ''),
(9, 'offend', 'cause bad feelings', 'оскорблять', '', 3, 0, 'v', 'Inappropriate choice of words may inadvently offend people.', ''),
(10, 'overdraft', 'deficit in a bank account', 'дефицит', '', 0, 0, 'n', 'Since I lost my job my card is always in overdraft.', ''),
(11, 'prejudice', 'inclination against', 'предубеждение', '', 1, 0, 'n', 'Prejudice against Gypsies is common in Russia.', ''),
(12, 'relent', 'become less severe', 'смягчаться', '', 0, 0, 'v', 'They may relent after seeing our apologies.', ''),
(13, 'reluctance', 'hesitation to do smth', 'нежелание', '', 0, 0, 'n', 'They answered with distinct reluctance.', ''),
(14, 'threaten', 'promise bad things to happen', 'угрожать', '', 0, 0, 'v', 'Smoking and heavy drinking threaten your health, you should stop them.', ''),
(15, 'violation', 'an act of breaking, esp. an agreement', 'несоблюдение', '', 2, 0, 'n', 'This was done in violation of Article 3 of the contract.', ''),
(16, 'accomplish', 'achieve, finalize', 'достигать', '', 0, 0, 'v', 'Your anger and profanities accomplish nothing.', ''),
(17, 'bow out', 'yield, retreat, surrender', 'откланяться', '', 0, 0, 'v', 'If you are determined to continue in this way all I can do is to bow out.', ''),
(18, 'climb the ladder', 'go up', 'взбираться по лестнице', '', 0, 0, 'v', '', ''),
(19, 'commute', 'go to and from work', 'добираться до работы', '', 0, 0, 'v', 'Most people commute for about 30 minutes one way every day.', ''),
(20, 'crucial', 'very important', 'критический', '', 0, 0, 'adj', 'It was a crucial decision for our business.', ''),
(21, 'drastic', 'very intense', 'решительный', '', 0, 0, 'adj', 'We lost money due to drastic over-calculations.', ''),
(22, 'expand', 'make wider and larger', 'расширять', '', 0, 0, 'v', 'There is no possibility to further expand our business in this town.', ''),
(23, 'gobble up', 'eat greedily', 'пожирать', '', 0, 0, 'v', 'This thing will gobble up all our money.', ''),
(24, 'interrupt', 'break the continuity of smth', 'прерывать', '', 0, 0, 'v', 'Sorry to interrupt you, but I need to go.', ''),
(25, 'mushroom', 'suddenly appear', 'всплывать', '', 0, 0, 'v', 'Losses started to mushroom in the second quarter.', ''),
(26, 'payoff', 'return on investment', 'доход по инвестициям', '', 0, 0, 'n', 'High payoff comes hand in hand with high risks.', ''),
(27, 'stakeholder', 'a person with an interest in smth', 'заинтересованное лицо', '', 0, 0, 'n', 'I am miself a stakeholder in this matter.', ''),
(28, 'trim', 'cut to length', 'подрезать', '', 0, 0, 'v', 'We need to trim our spendings if we want to ever finish the year without losses.', ''),
(29, 'trump', 'beat, win over', 'побеждать', '', 0, 0, 'v', 'Nothing but death can ever trump stupidity.', ''),
(30, 'unchecked', 'not restrained or controlled', 'неконтролируемый', '', 0, 0, 'adj', 'If left unchecked they will cost us millions.', '');

--
-- Индексы сохранённых таблиц
--

--
-- Индексы таблицы `progress`
--
ALTER TABLE `progress`
  ADD KEY `userId` (`userId`,`wordId`),
  ADD KEY `wordId` (`wordId`);

--
-- Индексы таблицы `unit`
--
ALTER TABLE `unit`
  ADD PRIMARY KEY (`id`),
  ADD KEY `startBookId` (`startBookId`,`finishBookId`),
  ADD KEY `finishBookId` (`finishBookId`);

--
-- Индексы таблицы `users`
--
ALTER TABLE `users`
  ADD PRIMARY KEY (`id`),
  ADD UNIQUE KEY `login` (`login`),
  ADD KEY `unit` (`unit`);

--
-- Индексы таблицы `words`
--
ALTER TABLE `words`
  ADD PRIMARY KEY (`num`),
  ADD KEY `synid` (`synid`),
  ADD KEY `oppid` (`oppid`);

--
-- Ограничения внешнего ключа сохраненных таблиц
--

--
-- Ограничения внешнего ключа таблицы `progress`
--
ALTER TABLE `progress`
  ADD CONSTRAINT `progress_ibfk_1` FOREIGN KEY (`userId`) REFERENCES `users` (`id`),
  ADD CONSTRAINT `progress_ibfk_2` FOREIGN KEY (`wordId`) REFERENCES `words` (`num`);

--
-- Ограничения внешнего ключа таблицы `unit`
--
ALTER TABLE `unit`
  ADD CONSTRAINT `unit_ibfk_1` FOREIGN KEY (`startBookId`) REFERENCES `words` (`num`),
  ADD CONSTRAINT `unit_ibfk_2` FOREIGN KEY (`finishBookId`) REFERENCES `words` (`num`);

--
-- Ограничения внешнего ключа таблицы `users`
--
ALTER TABLE `users`
  ADD CONSTRAINT `users_ibfk_1` FOREIGN KEY (`unit`) REFERENCES `unit` (`id`);

--
-- Ограничения внешнего ключа таблицы `words`
--
ALTER TABLE `words`
  ADD CONSTRAINT `words_ibfk_1` FOREIGN KEY (`synid`) REFERENCES `words` (`num`),
  ADD CONSTRAINT `words_ibfk_2` FOREIGN KEY (`oppid`) REFERENCES `words` (`num`);

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
