package com.br.ifpr.coffeenator.activitys;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.os.Handler;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.br.ifpr.coffeenator.R;
import com.br.ifpr.coffeenator.myclasses.MyAlertDialogConstructor;
import com.br.ifpr.coffeenator.myclasses.MyMediaPlayer;
import com.br.ifpr.coffeenator.myclasses.PS;

import java.util.ArrayList;
import java.util.Collections;

public class TrabalhandoActivity  extends AppCompatActivity {
    private int acertos = 0;
    private final int passoCodigo = 15;
    private int progresso = 0;
    private float dificuldade = 1.8f;

    private float tempoInicial = 10;
    private float tempo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        //Fullscreen
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);

        setContentView(R.layout.activity_trabalhando2);
        timerHandler.postDelayed(timerRunnable, 10);
        tempo = tempoInicial;
        dificuldade /= PS.getInteligencia();
    }

    public void onBtnProgramarTrabalhandoClick(View v){
        if(tempo > 0) {
            Button btn = (Button) v;
            int num = Integer.parseInt(btn.getText().toString());
            if (num == acertos + 1) {
                acertos++;
                if (acertos == 3) {
                    MyMediaPlayer.play(this, R.raw.digitando);
                    resetButtons();
                    acertos = 0;
                } else {
                    MyMediaPlayer.play(this, R.raw.digitando2);
                }
                progresso++;
                attProgress();
                attCodigo();
            } else {
                //adicionar som
                MyMediaPlayer.play(this, R.raw.erro_programando);
                progresso -= 3;
                acertos = 0;
                if (progresso < 0) {
                    progresso = 0;
                }
                attProgress();
                attCodigo();
                resetButtons();
            }
        }
    }

    private void resetButtons(){
        Button btn1 = (Button) findViewById(R.id.btn01);
        Button btn2 = (Button) findViewById(R.id.btn02);
        Button btn3 = (Button) findViewById(R.id.btn03);
        ArrayList<Integer> aleatorio = new ArrayList<Integer>();
        aleatorio.add(1);
        aleatorio.add(2);
        aleatorio.add(3);
        Collections.shuffle(aleatorio);
        btn1.setText(aleatorio.get(0).toString());
        btn2.setText(aleatorio.get(1).toString());
        btn3.setText(aleatorio.get(2).toString());
    }

    private void attCodigo(){
        String codigo = "struct group_info init_groups = { .usage = ATOMIC_INIT(2) };\n" +
                "\n" +
                "struct group_info *groups_alloc(int gidsetsize){\n" +
                "\n" +
                "\tstruct group_info *group_info;\n" +
                "\n" +
                "\tint nblocks;\n" +
                "\n" +
                "\tint i;\n" +
                "\n" +
                "\n" +
                "\n" +
                "\tnblocks = (gidsetsize + NGROUPS_PER_BLOCK - 1) / NGROUPS_PER_BLOCK;\n" +
                "\n" +
                "\t/* Make sure we always allocate at least one indirect block pointer */\n" +
                "\n" +
                "\tnblocks = nblocks ? : 1;\n" +
                "\n" +
                "\tgroup_info = kmalloc(sizeof(*group_info) + nblocks*sizeof(gid_t *), GFP_USER);\n" +
                "\n" +
                "\tif (!group_info)\n" +
                "\n" +
                "\t\treturn NULL;\n" +
                "\n" +
                "\tgroup_info->ngroups = gidsetsize;\n" +
                "\n" +
                "\tgroup_info->nblocks = nblocks;\n" +
                "\n" +
                "\tatomic_set(&group_info->usage, 1);\n" +
                "\n" +
                "\n" +
                "\n" +
                "\tif (gidsetsize <= NGROUPS_SMALL)\n" +
                "\n" +
                "\t\tgroup_info->blocks[0] = group_info->small_block;\n" +
                "\n" +
                "\telse {\n" +
                "\n" +
                "\t\tfor (i = 0; i < nblocks; i++) {\n" +
                "\n" +
                "\t\t\tgid_t *b;\n" +
                "\n" +
                "\t\t\tb = (void *)__get_free_page(GFP_USER);\n" +
                "\n" +
                "\t\t\tif (!b)\n" +
                "\n" +
                "\t\t\t\tgoto out_undo_partial_alloc;\n" +
                "\n" +
                "\t\t\tgroup_info->blocks[i] = b;\n" +
                "\n" +
                "\t\t}\n" +
                "\n" +
                "\t}\n" +
                "\n" +
                "\treturn group_info;\n" +
                "\n" +
                "\n" +
                "\n" +
                "out_undo_partial_alloc:\n" +
                "\n" +
                "\twhile (--i >= 0) {\n" +
                "\n" +
                "\t\tfree_page((unsigned long)group_info->blocks[i]);\n" +
                "\n" +
                "\t}\n" +
                "\n" +
                "\tkfree(group_info);\n" +
                "\n" +
                "\treturn NULL;\n" +
                "\n" +
                "}\n" +
                "\n" +
                "\n" +
                "\n" +
                "EXPORT_SYMBOL(groups_alloc);\n" +
                "\n" +
                "\n" +
                "\n" +
                "void groups_free(struct group_info *group_info)\n" +
                "\n" +
                "{\n" +
                "\n" +
                "\tif (group_info->blocks[0] != group_info->small_block) {\n" +
                "\n" +
                "\t\tint i;\n" +
                "\n" +
                "\t\tfor (i = 0; i < group_info->nblocks; i++)\n" +
                "\n" +
                "\t\t\tfree_page((unsigned long)group_info->blocks[i]);\n" +
                "\n" +
                "\t}\n" +
                "\n" +
                "\tkfree(group_info);\n" +
                "\n" +
                "}\n" +
                "\n" +
                "\n" +
                "\n" +
                "EXPORT_SYMBOL(groups_free);\n" +
                "\n" +
                "\n" +
                "\n" +
                "/* export the group_info to a user-space array */\n" +
                "\n" +
                "static int groups_to_user(gid_t __user *grouplist,\n" +
                "\n" +
                "\t\t\t  const struct group_info *group_info)\n" +
                "\n" +
                "{\n" +
                "\n" +
                "\tint i;\n" +
                "\n" +
                "\tunsigned int count = group_info->ngroups;\n" +
                "\n" +
                "\n" +
                "\n" +
                "\tfor (i = 0; i < group_info->nblocks; i++) {\n" +
                "\n" +
                "\t\tunsigned int cp_count = min(NGROUPS_PER_BLOCK, count);\n" +
                "\n" +
                "\t\tunsigned int len = cp_count * sizeof(*grouplist);\n" +
                "\n" +
                "\n" +
                "\n" +
                "\t\tif (copy_to_user(grouplist, group_info->blocks[i], len))\n" +
                "\n" +
                "\t\t\treturn -EFAULT;\n" +
                "\n" +
                "\n" +
                "\n" +
                "\t\tgrouplist += NGROUPS_PER_BLOCK;\n" +
                "\n" +
                "\t\tcount -= cp_count;\n" +
                "\n" +
                "\t}\n" +
                "\n" +
                "\treturn 0;\n" +
                "\n" +
                "}\n" +
                "\n" +
                "\n" +
                "\n" +
                "/* fill a group_info from a user-space array - it must be allocated already */\n" +
                "\n" +
                "static int groups_from_user(struct group_info *group_info,\n" +
                "\n" +
                "    gid_t __user *grouplist)\n" +
                "\n" +
                "{\n" +
                "\n" +
                "\tint i;\n" +
                "\n" +
                "\tunsigned int count = group_info->ngroups;\n" +
                "\n" +
                "\n" +
                "\n" +
                "\tfor (i = 0; i < group_info->nblocks; i++) {\n" +
                "\n" +
                "\t\tunsigned int cp_count = min(NGROUPS_PER_BLOCK, count);\n" +
                "\n" +
                "\t\tunsigned int len = cp_count * sizeof(*grouplist);\n" +
                "\n" +
                "\n" +
                "\n" +
                "\t\tif (copy_from_user(group_info->blocks[i], grouplist, len))\n" +
                "\n" +
                "\t\t\treturn -EFAULT;\n" +
                "\n" +
                "\n" +
                "\n" +
                "\t\tgrouplist += NGROUPS_PER_BLOCK;\n" +
                "\n" +
                "\t\tcount -= cp_count;\n" +
                "\n" +
                "\t}\n" +
                "\n" +
                "\treturn 0;\n" +
                "\n" +
                "}\n" +
                "\n" +
                "\n" +
                "\n" +
                "/* a simple Shell sort */\n" +
                "\n" +
                "static void groups_sort(struct group_info *group_info)\n" +
                "\n" +
                "{\n" +
                "\n" +
                "\tint base, max, stride;\n" +
                "\n" +
                "\tint gidsetsize = group_info->ngroups;\n" +
                "\n" +
                "\n" +
                "\n" +
                "\tfor (stride = 1; stride < gidsetsize; stride = 3 * stride + 1)\n" +
                "\n" +
                "\t\t; /* nothing */\n" +
                "\n" +
                "\tstride /= 3;\n" +
                "\n" +
                "\n" +
                "\n" +
                "\twhile (stride) {\n" +
                "\n" +
                "\t\tmax = gidsetsize - stride;\n" +
                "\n" +
                "\t\tfor (base = 0; base < max; base++) {\n" +
                "\n" +
                "\t\t\tint left = base;\n" +
                "\n" +
                "\t\t\tint right = left + stride;\n" +
                "\n" +
                "\t\t\tgid_t tmp = GROUP_AT(group_info, right);\n" +
                "\n" +
                "\n" +
                "\n" +
                "\t\t\twhile (left >= 0 && GROUP_AT(group_info, left) > tmp) {\n" +
                "\n" +
                "\t\t\t\tGROUP_AT(group_info, right) =\n" +
                "\n" +
                "\t\t\t\t    GROUP_AT(group_info, left);\n" +
                "\n" +
                "\t\t\t\tright = left;\n" +
                "\n" +
                "\t\t\t\tleft -= stride;\n" +
                "\n" +
                "\t\t\t}\n" +
                "\n" +
                "\t\t\tGROUP_AT(group_info, right) = tmp;\n" +
                "\n" +
                "\t\t}\n" +
                "\n" +
                "\t\tstride /= 3;\n" +
                "\n" +
                "\t}\n" +
                "\n" +
                "}\n" +
                "\n" +
                "\n" +
                "\n" +
                "/* a simple bsearch */\n" +
                "\n" +
                "int groups_search(const struct group_info *group_info, gid_t grp)\n" +
                "\n" +
                "{\n" +
                "\n" +
                "\tunsigned int left, right;\n" +
                "\n" +
                "\n" +
                "\n" +
                "\tif (!group_info)\n" +
                "\n" +
                "\t\treturn 0;\n" +
                "\n" +
                "\n" +
                "\n" +
                "\tleft = 0;\n" +
                "\n" +
                "\tright = group_info->ngroups;\n" +
                "\n" +
                "\twhile (left < right) {\n" +
                "\n" +
                "\t\tunsigned int mid = left + (right - left)/2;\n" +
                "\n" +
                "\t\tif (grp > GROUP_AT(group_info, mid))\n" +
                "\n" +
                "\t\t\tleft = mid + 1;\n" +
                "\n" +
                "\t\telse if (grp < GROUP_AT(group_info, mid))\n" +
                "\n" +
                "\t\t\tright = mid;\n" +
                "\n" +
                "\t\telse\n" +
                "\n" +
                "\t\t\treturn 1;\n" +
                "\n" +
                "\t}\n" +
                "\n" +
                "\treturn 0;\n" +
                "\n" +
                "}\n" +
                "\n" +
                "\n" +
                "\n" +
                "/**\n" +
                "\n" +
                " * set_groups - Change a group subscription in a set of credentials\n" +
                "\n" +
                " * @new: The newly prepared set of credentials to alter\n" +
                "\n" +
                " * @group_info: The group list to install\n" +
                "\n" +
                " *\n" +
                "\n" +
                " * Validate a group subscription and, if valid, insert it into a set\n" +
                "\n" +
                " * of credentials.\n" +
                "\n" +
                " */\n" +
                "\n" +
                "int set_groups(struct cred *new, struct group_info *group_info)\n" +
                "\n" +
                "{\n" +
                "\n" +
                "\tput_group_info(new->group_info);\n" +
                "\n" +
                "\tgroups_sort(group_info);\n" +
                "\n" +
                "\tget_group_info(group_info);\n" +
                "\n" +
                "\tnew->group_info = group_info;\n" +
                "\n" +
                "\treturn 0;\n" +
                "\n" +
                "}\n" +
                "\n" +
                "\n" +
                "\n" +
                "EXPORT_SYMBOL(set_groups);\n" +
                "\n" +
                "\n" +
                "\n" +
                "/**\n" +
                "\n" +
                " * set_current_groups - Change current's group subscription\n" +
                "\n" +
                " * @group_info: The group list to impose\n" +
                "\n" +
                " *\n" +
                "\n" +
                " * Validate a group subscription and, if valid, impose it upon current's task\n" +
                "\n" +
                " * security record.\n" +
                "\n" +
                " */\n" +
                "\n" +
                "int set_current_groups(struct group_info *group_info)\n" +
                "\n" +
                "{\n" +
                "\n" +
                "\tstruct cred *new;\n" +
                "\n" +
                "\tint ret;\n" +
                "\n" +
                "\n" +
                "\n" +
                "\tnew = prepare_creds();\n" +
                "\n" +
                "\tif (!new)\n" +
                "\n" +
                "\t\treturn -ENOMEM;\n" +
                "\n" +
                "\n" +
                "\n" +
                "\tret = set_groups(new, group_info);\n" +
                "\n" +
                "\tif (ret < 0) {\n" +
                "\n" +
                "\t\tabort_creds(new);\n" +
                "\n" +
                "\t\treturn ret;\n" +
                "\n" +
                "\t}\n" +
                "\n" +
                "\n" +
                "\n" +
                "\treturn commit_creds(new);\n" +
                "\n" +
                "}\n" +
                "\n" +
                "\n" +
                "\n" +
                "EXPORT_SYMBOL(set_current_groups);\n" +
                "\n" +
                "\n" +
                "\n" +
                "SYSCALL_DEFINE2(getgroups, int, gidsetsize, gid_t __user *, grouplist)\n" +
                "\n" +
                "{\n" +
                "\n" +
                "\tconst struct cred *cred = current_cred();\n" +
                "\n" +
                "\tint i;\n" +
                "\n" +
                "\n" +
                "\n" +
                "\tif (gidsetsize < 0)\n" +
                "\n" +
                "\t\treturn -EINVAL;\n" +
                "\n" +
                "\n" +
                "\n" +
                "\t/* no need to grab task_lock here; it cannot change */\n" +
                "\n" +
                "\ti = cred->group_info->ngroups;\n" +
                "\n" +
                "\tif (gidsetsize) {\n" +
                "\n" +
                "\t\tif (i > gidsetsize) {\n" +
                "\n" +
                "\t\t\ti = -EINVAL;\n" +
                "\n" +
                "\t\t\tgoto out;\n" +
                "\n" +
                "\t\t}\n" +
                "\n" +
                "\t\tif (groups_to_user(grouplist, cred->group_info)) {\n" +
                "\n" +
                "\t\t\ti = -EFAULT;\n" +
                "\n" +
                "\t\t\tgoto out;\n" +
                "\n" +
                "\t\t}\n" +
                "\n" +
                "\t}\n" +
                "\n" +
                "out:\n" +
                "\n" +
                "\treturn i;\n" +
                "\n" +
                "}\n" +
                "\n" +
                "\n" +
                "\n" +
                "/*\n" +
                "\n" +
                " *\tSMP: Our groups are copy-on-write. We can set them safely\n" +
                "\n" +
                " *\twithout another task interfering.\n" +
                "\n" +
                " */\n" +
                "\n" +
                "\n" +
                "\n" +
                "SYSCALL_DEFINE2(setgroups, int, gidsetsize, gid_t __user *, grouplist)\n" +
                "\n" +
                "{\n" +
                "\n" +
                "\tstruct group_info *group_info;\n" +
                "\n" +
                "\tint retval;\n" +
                "\n" +
                "\n" +
                "\n" +
                "\tif (!nsown_capable(CAP_SETGID))\n" +
                "\n" +
                "\t\treturn -EPERM;\n" +
                "\n" +
                "\tif ((unsigned)gidsetsize > NGROUPS_MAX)\n" +
                "\n" +
                "\t\treturn -EINVAL;\n" +
                "\n" +
                "\n" +
                "\n" +
                "\tgroup_info = groups_alloc(gidsetsize);\n" +
                "\n" +
                "\tif (!group_info)\n" +
                "\n" +
                "\t\treturn -ENOMEM;\n" +
                "\n" +
                "\tretval = groups_from_user(group_info, grouplist);\n" +
                "\n" +
                "\tif (retval) {\n" +
                "\n" +
                "\t\tput_group_info(group_info);\n" +
                "\n" +
                "\t\treturn retval;\n" +
                "\n" +
                "\t}\n" +
                "\n" +
                "\n" +
                "\n" +
                "\tretval = set_current_groups(group_info);\n" +
                "\n" +
                "\tput_group_info(group_info);\n" +
                "\n" +
                "\n" +
                "\n" +
                "\treturn retval;\n" +
                "\n" +
                "}\n" +
                "\n" +
                "\n" +
                "\n" +
                "/*\n" +
                "\n" +
                " * Check whether we're fsgid/egid or in the supplemental group..\n" +
                "\n" +
                " */\n" +
                "\n" +
                "int in_group_p(gid_t grp)\n" +
                "\n" +
                "{\n" +
                "\n" +
                "\tconst struct cred *cred = current_cred();\n" +
                "\n" +
                "\tint retval = 1;\n" +
                "\n" +
                "\n" +
                "\n" +
                "\tif (grp != cred->fsgid)\n" +
                "\n" +
                "\t\tretval = groups_search(cred->group_info, grp);\n" +
                "\n" +
                "\treturn retval;\n" +
                "\n" +
                "}\n" +
                "\n" +
                "\n" +
                "\n" +
                "EXPORT_SYMBOL(in_group_p);\n" +
                "\n" +
                "\n" +
                "\n" +
                "int in_egroup_p(gid_t grp)\n" +
                "\n" +
                "{\n" +
                "\n" +
                "\tconst struct cred *cred = current_cred();\n" +
                "\n" +
                "\tint retval = 1;\n" +
                "\n" +
                "\n" +
                "\n" +
                "\tif (grp != cred->egid)\n" +
                "\n" +
                "\t\tretval = groups_search(cred->group_info, grp);\n" +
                "\n" +
                "\treturn retval;\n" +
                "\n" +
                "}";
        addMessage(codigo.substring(0, progresso * passoCodigo));
    }

    private void addMessage(String msg) {
        TextView mTextView = (TextView) findViewById(R.id.txtCodigo);

        // Control c + Control v no forum :) (Funcionou)
        // append the new string
        mTextView.setText(msg);
        // find the amount we need to scroll.  This works by
        // asking the TextView's internal layout for the position
        // of the final line and then subtracting the TextView's height
        final int scrollAmount = mTextView.getLayout().getLineTop(mTextView.getLineCount()) - mTextView.getHeight();
        // if there is no need to scroll, scrollAmount will be <=0
        if (scrollAmount > 0)
            mTextView.scrollTo(0, scrollAmount);
        else
            mTextView.scrollTo(0, 0);
    }

    private void attProgress(){
        ProgressBar p = (ProgressBar) findViewById(R.id.progressBarProgramado);

        int x = Math.round(progresso / dificuldade);
        p.setProgress(x);

        if(x >= 100){
            Intent intent = new Intent(this, TrabalhoActivity.class);
            Bundle bundle = new Bundle();
            bundle.putBoolean("conseguiu", true);
            intent.putExtras(bundle);
            startActivity(intent);
        }
    }

    private void onEnd(){
        MyAlertDialogConstructor.showDeathMessage(getString(R.string.mortePorSerDemitido), this);
    }

    private void onFrameUpdate(){
        tempo -= 0.01f;
        int color = mixColors(tempo / tempoInicial);

        TextView txtCronometro = (TextView) findViewById(R.id.txtCronometro);
        txtCronometro.setTextColor(color);
        txtCronometro.setText("Prazo: "+ String.format("%.2f", tempo) + "s");
    }

    private int mixColors(double percent){
        double inverse_percent = 1.0 - percent;
        int color11 = ContextCompat.getColor(this, R.color.colorVerde);
        int color22 = ContextCompat.getColor(this, R.color.colorVermelho);
        int redPart = (int) (Color.red(color11)*percent + Color.red(color22)*inverse_percent);
        int greenPart = (int) (Color.green(color11)*percent + Color.green(color22)*inverse_percent);
        int bluePart = (int) (Color.blue(color11)*percent + Color.blue(color22)*inverse_percent);
        return Color.rgb(redPart, greenPart, bluePart);
    }

    Handler timerHandler = new Handler();
    Runnable timerRunnable = new Runnable() {

        @Override
        public void run() {
            if(tempo > 0) {
                onFrameUpdate();
                timerHandler.postDelayed(this, 10);
            } else {
                onEnd();
            }
        }
    };

    @Override
    public void onBackPressed() {
        // não chame o super desse método
    }

    @Override
    public void onPause(){
        timerHandler.removeCallbacks(timerRunnable);
        super.onPause();
    }


}
