import CSV.CSVReader;
import CSV.GameCSVReader;
import CSV.GameCSVWriter;
import classes.Game;
import classes.GameSpecs;
import enums.CPU;
import enums.GPU;
import enums.GameCategory;
import enums.RAM;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class Main {
    public static void main(String[] args) throws IOException {

//        StoreService storeService = StoreService.getInstance();
//        UserService userService = UserService.getInstance();
//
        Game rust = Game.builder()
                .title("Rust")
                .rating(91)
                .minSpecs(GameSpecs.builder().ram(RAM.GB8).cpu(CPU.I3).gpu(GPU.GTX1050).build())
                .recommendedSpecs(GameSpecs.builder().ram(RAM.GB16).cpu(CPU.I7).gpu(GPU.GTX2070).build())
                .categories(Arrays.asList(new GameCategory[]{GameCategory.SURVIVAL, GameCategory.ACTION, GameCategory.FPS}))
                .price(30.)
                .build();
        Game portal2 = Game.builder()
                .title("Portal2")
                .rating(98)
                .minSpecs(GameSpecs.builder().ram(RAM.GB4).cpu(CPU.I3).gpu(GPU.GTX1050).build())
                .recommendedSpecs(GameSpecs.builder().ram(RAM.GB8).cpu(CPU.I5).gpu(GPU.GTX1050).build())
                .categories(Arrays.asList(new GameCategory[]{GameCategory.PUZZLE, GameCategory.MULTIPLAYER, GameCategory.COOP}))
                .price(19.99)
                .build();
        Game terraria = Game.builder()
                .title("Terraria")
                .rating(98)
                .minSpecs(GameSpecs.builder().ram(RAM.GB2).cpu(CPU.I3).gpu(GPU.GTX1050).build())
                .recommendedSpecs(GameSpecs.builder().ram(RAM.GB4).cpu(CPU.I5).gpu(GPU.GTX1050).build())
                .categories(Arrays.asList(new GameCategory[]{GameCategory.SANDBOX, GameCategory.ADVENTURE}))
                .price(9.99)
                .build();
        Game csgo = Game.builder()
                .title("CS:GO")
                .rating(88)
                .minSpecs(GameSpecs.builder().ram(RAM.GB4).cpu(CPU.I3).gpu(GPU.GTX1050).build())
                .recommendedSpecs(GameSpecs.builder().ram(RAM.GB8).cpu(CPU.I5).gpu(GPU.GTX1050).build())
                .categories(Arrays.asList(new GameCategory[]{GameCategory.FPS, GameCategory.MULTIPLAYER, GameCategory.SHOOTER}))
                .price(0.)
                .build();
//
////        add games to store
//        storeService.addGame(csgo);
//        storeService.addGame(terraria);
//        storeService.addGame(rust);
//        storeService.addGame(portal2);
////        show most popular games
//        System.out.println("games by rating");
//        for (Game game: storeService.getMostPopular())
//            System.out.println(game);
////        show by game category
//        System.out.println("games by category");
//        for (Game game: storeService.getByCategory(Arrays.asList(new GameCategory[]{GameCategory.FPS}))) {
//            System.out.println(game);
//        }
//
////        register user
//        User meabefir = User.builder().username("meabefir").password("pass123").build();
//        userService.registerUser(meabefir);
////        add card to user
//        Card meabefirCard1 = Card.builder().number("1234-1234-5555-6666").pin("1234").type(CardType.DEBIT).currency(Currency.EURO).funds(1234.56).build();
////        logout user so it will throw error when adding card
//        userService.logoutUser(meabefir);
//        userService.addCard(meabefir, meabefirCard1);
//        System.out.println(meabefir);
////        login user
//        userService.loginUser(meabefir, meabefir.getPassword());
//        userService.addCard(meabefir, meabefirCard1);
//        System.out.println(meabefir);
////        wishlist game
//        storeService.wishlistGame(meabefir, terraria);
////        go on sale
//        storeService.goOnSale(terraria);
////        buy game
//        storeService.buyGame(meabefir, terraria);

//        new FileReader("games.csv")

//        CSVReader csvReader = new CSVReader("games.csv");
//        csvReader.test();


//        ///////////////////////////////////////////////////////////////////////////////////
//        ///////////////////////////////////////////////////////////////////////////////////
//        ///////////////////////////////////////////////////////////////////////////////////
//        CSV STUFF

//        READ
        GameCSVReader gameCSVReader = new GameCSVReader("games.csv");
        List<Game> games = gameCSVReader.parseData().stream().map(ob -> (Game)ob).collect(Collectors.toList());
        for (var game: games) {
            System.out.println(game);
        }

//        WRITE
//        GameCSVWriter gameCSVWriter = new GameCSVWriter("games.csv");
//
//        gameCSVWriter.write(terraria);
//        gameCSVWriter.write(rust);
//        gameCSVWriter.write(portal2);
//        gameCSVWriter.write(csgo);

    }
}
